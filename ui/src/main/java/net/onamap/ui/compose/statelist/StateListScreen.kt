package net.onamap.ui.compose.statelist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.onamap.models.models.StateData
import net.onamap.ui.model.States

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StateListScreen(
    onStateClicked: (StateData) -> Unit = {},
    states: List<StateData>
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "handstandsam.onamap.net")
                },
                colors = TopAppBarDefaults.topAppBarColors()
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            LazyColumn {
                items(states) { state ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
//                            StateImage(
//                                icon = state.drawableResThumb,
//                                sizeDp = 30.dp
//                            )
                        Text(
                            text = state.fullName,
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(
                                start = 8.dp
                            )
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun StateListScreenPreview() {
    StateListScreen(
        states = States.states.subList(0, 10)
    )
}