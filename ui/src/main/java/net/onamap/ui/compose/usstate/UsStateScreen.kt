package net.onamap.android.compose.usstate

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.onamap.android.compose.usstate.StateHeaderCard.StateHeaderCard
import net.onamap.models.models.Photo
import net.onamap.models.models.StateData

data class StateHeaderInfo(@DrawableRes val icon: Int, val photoCount: Int)

object UsStateScreen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun UsStateScreen(
        onUpClicked: () -> Unit = {},
        state: StateData,
        photos: List<Photo>
    ) {
        val itemsList = mutableListOf<Any>(
            StateHeaderInfo(
                icon = state.icon,
                photoCount = photos.size
            )
        ).apply {
            addAll(photos)
        }.toList()


        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = state.fullName)
                    },
                    actions = {
                        IconButton(onClick = {
                            onUpClicked()
                        }) {
                            Icon(Icons.Filled.ArrowBack, null)
                        }
                    }
                )
            },
            content = { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    Row {
                        itemsList.forEach { item ->
                            when (item) {
                                is Photo -> PhotoCardView(item)
                                is StateHeaderInfo -> StateHeaderCard(
                                    icon = item.icon,
                                    photoCount = item.photoCount
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}