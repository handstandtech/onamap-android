package net.onamap.android

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import net.onamap.android.dao.MapDao
import net.onamap.android.model.StateData

class WebViewActivity : AppCompatActivity() {
    private var mapDao: MapDao? = null
    private var webView: WebView? = null
    private var adapter: StateDataListAdapter? = null

    inner class MyWebViewClient internal constructor(private val context: Activity) : WebViewClient() {
        override fun onPageFinished(view: WebView, url: String) {
            for (curr in mapDao!!.getValues()!!) {
                updateWebviewOnState(curr)
            }

            //hide loading image
            context.findViewById<View>(R.id.loading_spinner).visibility = View.GONE
            //show webview
            context.findViewById<View>(R.id.webview).visibility = View.VISIBLE
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        mapDao = MapDao(applicationContext)
        adapter = StateDataListAdapter(
            this,
            R.layout.list_view_item_row, mapDao!!
        )
        mapDao!!.setAdapter(adapter)
        webView = findViewById<View>(R.id.webview) as WebView
        webView!!.settings.javaScriptEnabled = true
        webView!!.loadUrl("file:///android_asset/map.html")
        webView!!.setBackgroundColor(0x00000000)
        webView!!.webChromeClient = WebChromeClient()
        webView!!.settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        webView!!.setInitialScale(100)
        webView!!.webViewClient = MyWebViewClient(this)

        //Inject WebAppInterface methods into Web page by having Interface name 'Android'
        webView!!.addJavascriptInterface(WebAppInterface(), "Android")
        val listView =
            findViewById<View>(R.id.listview) as ListView
        listView.onItemClickListener = OnItemClickListener { adapterView, view, position, id ->
            val curr = mapDao!!.getValues()!![position]
            curr.toggle()
            mapDao!!.updateState(curr)
            updateWebviewOnState(curr)
        }

        // Assign adapter to ListView
        listView.adapter = adapter
    }

    public override fun onResume() {
        super.onResume()
    }

    private fun updateWebviewOnState(stateData: StateData) {
        val javascriptCommand =
            "WebView.stateClickedExternal('" + stateData.abbv + "', " + stateData.selected + ");"
        webView!!.post { webView!!.loadUrl("javascript:$javascriptCommand") }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if generateVectorDrawable is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    inner class WebAppInterface {
        @JavascriptInterface
        fun stateClicked(stateAbbv: String?) {
            var stateData = mapDao!!.getState(stateAbbv)
            stateData!!.toggle()
            stateData = mapDao!!.updateState(stateData)
            updateWebviewOnState(stateData)
        }
    }
}