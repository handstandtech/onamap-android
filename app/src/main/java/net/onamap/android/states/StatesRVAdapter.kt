package net.onamap.android.states

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.onamap.android.R
import net.onamap.android.model.StateData
import net.onamap.android.model.States

internal class StatesRVAdapter : RecyclerView.Adapter<StatesViewHolder>() {
    private var stateDatas: List<StateData> = States.states

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_view_item_row, parent, false)
        return StatesViewHolder(view)
    }

    fun setData(stateDatas: List<StateData>) {
        this.stateDatas = stateDatas
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: StatesViewHolder, position: Int) {
        holder.bindData(stateDatas[position], position)
    }

    override fun getItemCount(): Int {
        return stateDatas.size
    }
}
