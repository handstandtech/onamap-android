package net.onamap.android


import androidx.compose.runtime.Composable
//import com.github.zsoltk.compose.router.Router
import net.onamap.android.compose.statelist.StateListScreen
import net.onamap.models.models.StateData
import net.onamap.android.model.States

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
//                    backStack.push(
//                        Routing.State(state)
//                    )
                },
                states = States.states
            )
            stateListComposable
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