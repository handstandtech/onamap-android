package net.onamap.ui.compose.usstate

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.onamap.android.compose.usstate.PhotoCardView
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
        println("Photos $photos")
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
                    navigationIcon = {
                        IconButton(onClick = {
                            onUpClicked()
                        }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                        }
                    },
                )
            },
            content = { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    LazyColumn {
                        itemsList.forEach { listItem ->
                            item {
                                when (listItem) {
                                    is Photo -> PhotoCardView(listItem)
                                    is StateHeaderInfo -> StateHeaderCard(
                                        icon = listItem.icon,
                                        photoCount = listItem.photoCount
                                    )
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}