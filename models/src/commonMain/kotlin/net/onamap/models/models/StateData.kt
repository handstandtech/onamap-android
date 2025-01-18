package net.onamap.models.models

import kotlinx.serialization.Serializable

@Serializable
data class StateData(
    val abbv: String,
    val fullName: String,
    val drawableResThumb: Int,
    val icon: Int
) {
    var selected = false
    fun toggle() {
        selected = !selected
    }

}