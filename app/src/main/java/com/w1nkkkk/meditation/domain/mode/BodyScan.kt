package com.w1nkkkk.meditation.domain.mode

import androidx.compose.runtime.Composable
import com.w1nkkkk.meditation.R

class BodyScan : MeditationMode(
    name = R.string.body_scan,
    description = R.string.body_scan_description
) {
    override fun meditate() {}

    @Composable
    override fun MeditationUI() {}
}