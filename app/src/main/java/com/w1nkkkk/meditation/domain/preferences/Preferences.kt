package com.w1nkkkk.meditation.domain.preferences

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import com.w1nkkkk.meditation.presentation.component.DateObject

data class Preferences(
    val meditaitionTime : MutableState<Float>,
    var updateDaysCount : Boolean,
    var toDayDate : String
) {
    constructor() : this(mutableFloatStateOf(0f), false,
        DateObject.convertLongToTime(DateObject.currentTimeToLong()))
}