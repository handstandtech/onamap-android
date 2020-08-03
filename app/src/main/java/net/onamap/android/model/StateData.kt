package net.onamap.android.model

import java.io.Serializable

class StateData(
    val abbv: String,
    val fullName: String,
    val drawableResThumb: Int,
    val icon: Int
) : Serializable {
    var selected = false
    fun toggle() {
        selected = !selected
    }

}