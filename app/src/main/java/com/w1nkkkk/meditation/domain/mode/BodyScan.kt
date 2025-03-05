package com.w1nkkkk.meditation.domain.mode

import androidx.compose.runtime.Composable
import com.w1nkkkk.meditation.R

/*
Это guided-медитация, которая ведет пользователя через последовательное
осознание различных частей тела. Приложение будет воспроизводить голос,
который дает инструкции по сканированию тела сверху вниз (например, от макушки головы
до кончиков пальцев ног) или наоборот.
 */

class BodyScan : MeditationMode(
    name = R.string.body_scan,
    description = R.string.body_scan_description
) {
    override fun meditate() {}

    @Composable
    override fun MeditationUI() {}
}