package com.w1nkkkk.meditation.data.entity

data class PreferencesModel(
    val meditationTime : Long,
    val updateDaysCount : Boolean,
    val toDayDate : Long
) {
    constructor() : this(0, true, 0)
}
