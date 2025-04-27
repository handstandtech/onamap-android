package net.onamap.android

//import com.github.zsoltk.compose.router.Router
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.MutableStateFlow
import net.onamap.android.dao.PhotoDao
import net.onamap.android.model.States
import net.onamap.models.models.StateData
import net.onamap.ui.compose.statelist.StateListScreen
import net.onamap.ui.compose.usstate.UsStateScreen.UsStateScreen

interface Root {

    sealed class Routing {
        data object UsMap : Routing()

        data class State(val state: StateData) : Routing()

        companion object {

            val currRoute: MutableStateFlow<Routing> = MutableStateFlow(UsMap)
        }
    }

    companion object {


        @Composable
        fun Content() {
            val route = Routing.currRoute.collectAsState().value
            when (route) {
                is Routing.UsMap -> {
                    StateListScreen(
                        onStateClicked = { state ->
                            println("State Pressed")
                            Routing.currRoute.value = Routing.State(state)
                            // TODO: Implement navigation
                        },
                        states = States.states
                    )
                }

                is Routing.State -> {
                    val state = route.state
                    val photos = PhotoDao.getPhotosForState(state.fullName)
                    UsStateScreen(
                        state = state,
                        photos = photos,
                        onUpClicked = {
                            Routing.currRoute.value = Routing.UsMap
                        }
                    )
                }
            }
        }
    }
}

//            val stateListComposable = StateListScreen(
//                onStateClicked = { state ->
//                    println("State Pressed")
//                    // TODO: Implement navigation
//                },
//                states = States.states
//            )
//            stateListComposable
//
//            // TODO: Implement proper navigation
////             For now, just show Virginia as an example
//            val state = "Virginia"
//            val photos = PhotoDao.getPhotosForState(state)
//            UsStateScreen.UsStateScreen(
//                state = States.states.find { it.fullName == state }!!,
//                photos = photos,
//                onUpClicked = {
//                    // TODO: Implement back navigation
//                },
//            )
//        }

