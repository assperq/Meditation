package com.w1nkkkk.meditation.domain.mode

import androidx.compose.runtime.Composable
import com.w1nkkkk.meditation.R

class NatureSoundMeditation : MeditationMode(
    name = R.string.nature_sound_meditation,
    description = R.string.nature_sound_meditation_description
) {
    override fun meditate() {}

    @Composable
    override fun MeditationUI() {}
}