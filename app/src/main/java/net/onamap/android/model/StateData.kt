package net.onamap.android.model

import java.io.Serializable

class StateData(var abbv: String, var fullName: String, var drawableResThumb: Int, var icon: Int) :
    Serializable {
    var selected = false
    fun toggle() {
        selected = !selected
    }

}