package com.w1nkkkk.meditation.domain.mode

import androidx.compose.runtime.Composable
import com.w1nkkkk.meditation.R

class SilentMeditation : MeditationMode(
    name = R.string.silent_meditation,
    description = R.string.silent_meditation_description
) {
    override fun meditate() {}

    @Composable
    override fun MeditationUI() {}
}