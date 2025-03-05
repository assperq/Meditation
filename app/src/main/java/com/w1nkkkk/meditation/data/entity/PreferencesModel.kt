package com.w1nkkkk.meditation.data.entity

data class PreferencesModel(
    val meditationTime : Long
) {
    constructor() : this(0)
}
