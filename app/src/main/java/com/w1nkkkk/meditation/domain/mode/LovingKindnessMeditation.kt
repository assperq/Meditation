package com.w1nkkkk.meditation.domain.mode

import androidx.compose.runtime.Composable
import com.w1nkkkk.meditation.R

class LovingKindnessMeditation : MeditationMode(
    name = R.string.loving_kindness_meditation,
    description = R.string.loving_kindness_meditation_description
) {
    override fun meditate() {}

    @Composable
    override fun MeditationUI() {}
}