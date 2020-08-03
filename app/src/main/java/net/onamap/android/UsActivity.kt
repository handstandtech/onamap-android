package net.onamap.android

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import net.onamap.android.dao.MapDao
import net.onamap.android.model.StateData
import net.onamap.android.model.States

class UsActivity : BaseActivity() {
    private val mapDao: MapDao? = null
    private var adapter: ArrayAdapter<StateData?>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usmap)
        adapter = object :
            ArrayAdapter<StateData?>(applicationContext, R.layout.list_view_item_row, States.states) {
            override fun getView(position: Int, row: View?, parent: ViewGroup): View {
                var row = row
                var holder: ViewHolder? = null
                if (row == null) {
                    row = LayoutInflater.from(this.context)
                        .inflate(R.layout.list_view_item_row, parent, false)
                    holder = ViewHolder()
                    holder.imgIcon = row.findViewById<View>(R.id.imgIcon) as ImageView
                    holder.txtTitle = row.findViewById<View>(R.id.txtTitle) as TextView
                    row.tag = holder
                } else {
                    holder = row.tag as ViewHolder
                }
                val stateData = getItem(position)
                if (stateData != null) {
                    holder.txtTitle!!.text = stateData.fullName
                    holder!!.imgIcon!!.setImageResource(stateData.drawableResThumb)
                }
                return row!!
            }
        }
        val listView =
            findViewById<View>(R.id.listview) as ListView
        listView.onItemClickListener = OnItemClickListener { adapterView, view, position, id ->
            val curr = adapter!!.getItem(position)
            val intent = Intent(this@UsActivity, StateActivity::class.java)
            intent.putExtra("state", curr)
            startActivity(intent)
        }

        // Assign adapter to ListView
        listView.adapter = adapter
    }

    internal class ViewHolder {
        var imgIcon: ImageView? = null
        var txtTitle: TextView? = null
    }
}