package net.onamap.android


import androidx.compose.runtime.Composable
import com.github.zsoltk.compose.router.Router
import net.onamap.android.compose.UsState
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
                when (val currentRouting = backStack.last()) {
                    is Routing.UsMap -> {
                        StatesList(
                            onStateClicked = { state ->
                                backStack.push(
                                    Routing.State(state)
                                )
                            },
                            states = States.states
                        )
//                        LoggedOut.Content(
//                            defaultRouting = Splash,
//                            onLoggedIn = { user ->
//                                // play around with other back stack operations here:
//                                backStack.newRoot(
//                                    Routing.LoggedIn(user)
//                                )
//                            }
//                        )
                    }
                    is Routing.State -> {
                        val state = currentRouting.state
                        UsState(
                            state = state,
                            photos = listOf(),

                            onUpClicked = {
                                backStack.pop()
                            }
                        )
//                        LoggedIn.Content(
//                            defaultRouting = Gallery,
//                            user = currentRouting.user,
//                            onLogout = {
//                                // play around with other back stack operations here:
//                                backStack.newRoot(
//                                    Routing.LoggedOut
//                                )
//                            }
//                        )
                    }
                }
            }
        }
    }
}