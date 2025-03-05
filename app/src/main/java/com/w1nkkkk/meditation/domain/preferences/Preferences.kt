package com.w1nkkkk.meditation.domain.preferences

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf

data class Preferences(
    val meditaitionTime : MutableState<Float>
) {
    constructor() : this(mutableFloatStateOf(0f))
}