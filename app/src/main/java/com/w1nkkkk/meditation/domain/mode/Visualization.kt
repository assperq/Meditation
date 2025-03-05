package com.w1nkkkk.meditation.domain.mode

import androidx.compose.runtime.Composable
import com.w1nkkkk.meditation.R

/*
Это медитация, основанная на работе с воображением.
Приложение воспроизводит голосовые инструкции и подходящий звуковой фон,
помогая пользователю создавать в своем воображении определенные образы
 */

class Visualization : MeditationMode(
    name = R.string.visualization,
    description = R.string.visualization_description
) {
    override fun meditate() {}

    @Composable
    override fun MeditationUI() {}
}