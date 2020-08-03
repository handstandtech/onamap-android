package net.onamap.android

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import net.onamap.android.dao.MapDao
import net.onamap.android.model.StateData

class StateDataListAdapter(private val activity: Activity, var layoutResourceId: Int, private val mapDao: MapDao) :
    ArrayAdapter<StateData?>(activity, layoutResourceId, mapDao.getValues()!!.toTypedArray()) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val stateData = mapDao.getValues()!![position]
        Log.v(TAG, "State: " + stateData.fullName)
        var row = convertView
        var holder: ViewHolder? = null
        if (row == null) {
            Log.v(TAG, "Inflating.")
            val inflater = activity.layoutInflater
            row = inflater.inflate(layoutResourceId, parent, false)
            holder = ViewHolder()
            holder.imgIcon = row.findViewById<View>(R.id.imgIcon) as ImageView
            holder.txtTitle = row.findViewById<View>(R.id.txtTitle) as TextView
            row.tag = holder
        } else {
            Log.v(TAG, "Previously Inflated")
            holder = row.tag as ViewHolder
        }
        holder!!.txtTitle!!.text = stateData.fullName + " - " + stateData.selected
        holder.imgIcon!!.setImageResource(stateData.icon)
        return row!!
    }

    internal class ViewHolder {
        var imgIcon: ImageView? = null
        var txtTitle: TextView? = null
    }

    companion object {
        private val TAG = StateDataListAdapter::class.java.simpleName
    }

}