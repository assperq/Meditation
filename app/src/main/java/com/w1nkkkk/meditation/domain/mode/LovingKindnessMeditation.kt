package com.w1nkkkk.meditation.domain.mode

import androidx.compose.runtime.Composable
import com.w1nkkkk.meditation.R

/*
Это медитация, которая направлена на развитие чувства доброты и сострадания
к себе и другим. Приложение воспроизводит голосовую медитацию,
в которой предлагаются мантры или фразы, направленные на развитие любви и сострадания.
 */

class LovingKindnessMeditation : MeditationMode(
    name = R.string.loving_kindness_meditation,
    description = R.string.loving_kindness_meditation_description
) {
    override fun meditate() {}

    @Composable
    override fun MeditationUI() {}
}