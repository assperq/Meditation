package com.w1nkkkk.meditation.domain.mode

import androidx.compose.runtime.Composable
import com.w1nkkkk.meditation.R

class WalkingMeditation : MeditationMode(
    name = R.string.walking_meditation,
    description = R.string.walking_meditation_description
) {
    override fun meditate() {}

    @Composable
    override fun MeditationUI() {}
}