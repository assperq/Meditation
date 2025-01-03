package com.w1nkkkk.meditation.domain.mode

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.w1nkkkk.meditation.R

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