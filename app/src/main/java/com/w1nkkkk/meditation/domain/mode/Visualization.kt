package com.w1nkkkk.meditation.domain.mode

import androidx.compose.runtime.Composable
import com.w1nkkkk.meditation.R

class Visualization : MeditationMode(
    name = R.string.visualization,
    description = R.string.visualization_description
) {
    override fun meditate() {}

    @Composable
    override fun MeditationUI() {}
}