package net.onamap.android.compose

import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview
import net.onamap.android.R
import net.onamap.android.compose.StateHeaderCard.StateHeaderCard

@Preview
@Composable
private fun AlaskaStateHeaderCardPreview() {
    StateHeaderCard(icon = R.drawable.ak, photoCount = 1)
}

@Preview
@Composable
private fun CaliforniaStateHeaderCardPreview() {
    StateHeaderCard(icon = R.drawable.ca, photoCount = 90)
}