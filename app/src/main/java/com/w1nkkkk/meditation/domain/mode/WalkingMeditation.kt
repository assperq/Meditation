package com.w1nkkkk.meditation.domain.mode

import androidx.compose.runtime.Composable
import com.w1nkkkk.meditation.R

/*
Этот режим предназначен для медитации во время ходьбы.
Приложение может предложить тихий звуковой фон,
подходящий для прогулки, и будет озвучивать инструкции о том, как осознанно двигаться.
 */

class WalkingMeditation : MeditationMode(
    name = R.string.walking_meditation,
    description = R.string.walking_meditation_description
) {
    override fun meditate() {}

    @Composable
    override fun MeditationUI() {}
}