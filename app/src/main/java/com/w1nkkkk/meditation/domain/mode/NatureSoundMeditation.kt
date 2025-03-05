package com.w1nkkkk.meditation.domain.mode

import androidx.compose.runtime.Composable
import com.w1nkkkk.meditation.R

/*
Этот режим сфокусирован на прослушивании звуков природы.
Приложение предлагает пользователю различные звуки природы
(шум дождя, пение птиц, шум моря, потрескивание костра) или их комбинации.
Пользователь может выбрать длительность медитации
 */

class NatureSoundMeditation : MeditationMode(
    name = R.string.nature_sound_meditation,
    description = R.string.nature_sound_meditation_description
) {
    override fun meditate() {}

    @Composable
    override fun MeditationUI() {}
}