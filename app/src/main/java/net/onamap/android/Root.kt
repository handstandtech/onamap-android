package net.onamap.android


import androidx.compose.runtime.Composable
import com.github.zsoltk.compose.router.Router
import net.onamap.android.compose.statelist.StateListScreen
import net.onamap.android.compose.usstate.UsStateScreen.UsStateScreen
import net.onamap.android.dao.PhotoDao
import net.onamap.android.model.StateData
import net.onamap.android.model.States

interface Root {

    sealed class Routing {
        object UsMap : Routing()

        data class State(val state: StateData) : Routing()
    }

    companion object {
        @Composable
        fun Content(defaultRouting: Routing) {
            Router(defaultRouting) { backStack ->
                val stateListComposable = StateListScreen(
                    onStateClicked = { state ->
                        backStack.push(
                            Routing.State(state)
                        )
                    },
                    states = States.states
                )
                when (val currentRouting = backStack.last()) {
                    is Routing.UsMap -> {
                        stateListComposable
                    }
                    is Routing.State -> {
                        val state = currentRouting.state
                        val photos = PhotoDao.getPhotosForState(state.fullName)
                        UsStateScreen(
                            state = state,
                            photos = photos,
                            onUpClicked = {
                                backStack.pop()
                            }
                        )
                    }
                }
            }
        }
    }
}