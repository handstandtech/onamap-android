package net.onamap.android.dao

import android.content.Context
import android.content.SharedPreferences
import android.os.Handler
import android.os.Looper
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import net.onamap.android.StateDataListAdapter
import net.onamap.android.model.StateData
import net.onamap.android.model.States
import java.util.*

class MapDao(private val context: Context) {
    private val settings: SharedPreferences
    private var adapter: StateDataListAdapter? = null
    private var values: MutableList<StateData>? = null
    private val gson = GsonBuilder().setPrettyPrinting().create()
    fun setAdapter(adapter: StateDataListAdapter?) {
        this.adapter = adapter
    }

    fun getValues(): List<StateData>? {
        return values
    }

    private fun loadValues() {
        if (values == null) {
            //get the sharepref
            val valuesJson = settings.getString(MAP_VALUES, null)
            values = if (valuesJson != null) {
                gson.fromJson<MutableList<StateData>>(
                    valuesJson,
                    object : TypeToken<List<StateData?>?>() {}.type
                )
            } else {
                defaultValues()
            }
        }
    }

    private fun saveValues() {
        //set the sharedpref
        val editor = settings.edit()
        editor.putString(MAP_VALUES, gson.toJson(values))
        editor.commit()
    }

    private fun defaultValues(): MutableList<StateData> {
        val list: MutableList<StateData> = ArrayList()
        for ((_, value) in States.map) {
            list.add(value)
        }
        Collections.sort(list) { lhs, rhs -> lhs.fullName.compareTo(rhs.fullName) }
        return list
    }

    fun getState(stateAbbv: String?): StateData? {
        for (stateData in getValues()!!) {
            if (stateData.abbv.equals(stateAbbv, ignoreCase = true)) {
                return stateData
            }
        }
        return null
    }

    fun getIdxOfState(stateAbbv: String?): Int {
        for (i in values!!.indices) {
            val stateData = values!![i]
            if (stateData.abbv.equals(stateAbbv, ignoreCase = true)) {
                return i
            }
        }
        return -1
    }

    fun updateState(pStateData: StateData): StateData {
        val index = getIdxOfState(pStateData.abbv)
        values!![index] = pStateData
        saveValues()
        Handler(Looper.getMainLooper()).post { adapter!!.notifyDataSetChanged() }
        return pStateData
    }

    companion object {
        private val TAG = MapDao::class.java.simpleName
        private const val MAP_VALUES = "MAP_VALUES"
    }

    init {
        settings = context.getSharedPreferences(MAP_VALUES, Context.MODE_PRIVATE)
        loadValues()
    }
}