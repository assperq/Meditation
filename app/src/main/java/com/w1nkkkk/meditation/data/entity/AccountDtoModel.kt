package com.w1nkkkk.meditation.data.entity

data class AccountDtoModel(
    val name : String,
    val day_count : Int,
    val achievements : Map<String, String>,
    val emotion_state : Int
) {
    constructor() : this(
        "",
        0,
        hashMapOf(),
        0
    )
}
