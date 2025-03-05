package com.w1nkkkk.meditation.domain.mode

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.w1nkkkk.meditation.R

/*
Это базовый режим, который помогает
сосредоточиться на текущем моменте через наблюдение за дыханием.
Пользователю предлагается таймер, который можно настроить (например, 5, 10, 15 минут).
Приложение может предлагать тихий звук колокольчика в начале и конце сессии. В процессе
медитации может звучать очень тихий, нежный звуковой фон,
имитирующий шум природы или спокойную музыку.
 */

class MindfulBreathing : MeditationMode(
    name = R.string.mindful_breathing,
    description = R.string.mindful_breathing_description
) {
    override fun meditate() {}

    @Composable
    override fun MeditationUI() {
        Text("MIND_MEDITATION", fontSize = 30.sp)
    }
}