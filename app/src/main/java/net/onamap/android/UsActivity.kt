package net.onamap.android

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.onamap.android.dao.MapDao
import net.onamap.android.states.StatesRVAdapter

class UsActivity : BaseActivity() {

    private var recyclerViewAdapter: StatesRVAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usmap)
        val recyclerView = findViewById<View>(R.id.states_recyclerview) as RecyclerView
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewAdapter = StatesRVAdapter()
        recyclerView.adapter = recyclerViewAdapter
    }
}