package net.onamap.android.compose.usstate

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Icon
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import net.onamap.android.compose.usstate.StateHeaderCard.StateHeaderCard
import net.onamap.android.dao.Photo
import net.onamap.android.model.StateData

data class StateHeaderInfo(@DrawableRes val icon: Int, val photoCount: Int)

object UsStateScreen {
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
                    navigationIcon = {
                        IconButton(onClick = {
                            onUpClicked()
                        }) {
                            Icon(Icons.Filled.ArrowBack)
                        }
                    }
                )
            },
            bodyContent = {
                LazyColumnFor(items = itemsList) { item ->
                    when (item) {
                        is Photo -> PhotoCardView(item)
                        is StateHeaderInfo -> StateHeaderCard(
                            icon = item.icon,
                            photoCount = item.photoCount
                        )
                    }

                }
            }
        )
    }
}