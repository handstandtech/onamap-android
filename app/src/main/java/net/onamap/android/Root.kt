package net.onamap.android

import androidx.compose.runtime.Composable
import net.onamap.android.dao.PhotoDao
//import com.github.zsoltk.compose.router.Router
import net.onamap.ui.compose.statelist.StateListScreen
import net.onamap.models.models.StateData
import net.onamap.ui.model.States
import net.onamap.android.compose.usstate.UsStateScreen

interface Root {

    sealed class Routing {
        object UsMap : Routing()

        data class State(val state: StateData) : Routing()
    }

    companion object {
        @Composable
        fun Content(defaultRouting: Routing) {
            val stateListComposable = StateListScreen(
                onStateClicked = { state ->
                    println("State Pressed")
                    // TODO: Implement navigation
                },
                states = States.states
            )
            stateListComposable

            // TODO: Implement proper navigation
            // For now, just show Virginia as an example
            val photos = PhotoDao.getPhotosForState("Virginia")
            val virginiaState = States.states.find { it.fullName == "Virginia" }
            virginiaState?.let { state ->
                UsStateScreen.UsStateScreen(
                    state = state,
                    photos = photos,
                    onUpClicked = {
                        // TODO: Implement back navigation
                    }
                )
            }
//            Router(defaultRouting) { backStack ->
//
//                when (val currentRouting = backStack.last()) {
//                    is Routing.UsMap -> {
//                        stateListComposable
//                    }
//                    is Routing.State -> {
//                        val state = currentRouting.state
//                        val photos = PhotoDao.getPhotosForState(state.fullName)
//                        UsStateScreen(
//                            state = state,
//                            photos = photos,
//                            onUpClicked = {
//                                backStack.pop()
//                            }
//                        )
//                    }
//                }
//            }
        }
    }
}