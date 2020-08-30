package net.onamap.android.states

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import net.onamap.android.R
import net.onamap.android.compose.ComposeStateActivity
import net.onamap.android.model.StateData


internal class StatesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val textView: TextView = itemView.findViewById(R.id.state_name)

    private val imageView: AppCompatImageView = itemView.findViewById(R.id.state_icon)

    lateinit var stateData: StateData

    init {
        itemView.setOnClickListener {
            val context = itemView.context

//            val intent = Intent(context, StateActivity::class.java)
            val intent = Intent(context, ComposeStateActivity::class.java)
            intent.putExtra("state", stateData)
            context.startActivity(intent)
        }
    }

    fun bindData(stateData: StateData, position: Int) {
        this.stateData = stateData

        imageView.setImageResource(stateData.drawableResThumb)
        textView.text = stateData.fullName
    }
}