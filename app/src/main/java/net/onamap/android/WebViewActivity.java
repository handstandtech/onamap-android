package net.onamap.android;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;

import net.onamap.android.dao.MapDao;
import net.onamap.android.model.StateData;

public class WebViewActivity extends AppCompatActivity {

    private MapDao mapDao;
    private WebView webView;
    private StateDataListAdapter adapter;

    public class MyWebViewClient extends WebViewClient {

        private final Activity context;

        MyWebViewClient(Activity context) {
            this.context = context;
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            for (StateData curr : mapDao.getValues()) {
                updateWebviewOnState(curr);
            }
            
            //hide loading image
            context.findViewById(R.id.loading_spinner).setVisibility(View.GONE);
            //show webview
            context.findViewById(R.id.webview).setVisibility(View.VISIBLE);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        mapDao = new MapDao(getApplicationContext());
        adapter = new StateDataListAdapter(this,
                R.layout.list_view_item_row, mapDao);
        mapDao.setAdapter(adapter);

        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/map.html");
        webView.setBackgroundColor(0x00000000);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.setInitialScale(100);
        webView.setWebViewClient(new MyWebViewClient(this));

        //Inject WebAppInterface methods into Web page by having Interface name 'Android'
        webView.addJavascriptInterface(new WebAppInterface(), "Android");

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position,
                                    long id) {
                StateData curr = mapDao.getValues().get(position);
                curr.toggle();
                mapDao.updateState(curr);
                updateWebviewOnState(curr);
            }

        });

        // Assign adapter to ListView
        listView.setAdapter(adapter);
    }

    public void onResume() {
        super.onResume();
    }

    private void updateWebviewOnState(StateData stateData) {
        final String javascriptCommand = "WebView.stateClickedExternal('" + stateData.abbv + "', " + stateData.selected + ");";
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("javascript:" + javascriptCommand);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if generateVectorDrawable is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class WebAppInterface {

        @JavascriptInterface
        public void stateClicked(String stateAbbv) {
            StateData stateData = mapDao.getState(stateAbbv);
            stateData.toggle();
            stateData = mapDao.updateState(stateData);
            updateWebviewOnState(stateData);
        }
    }
}
