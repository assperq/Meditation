package com.w1nkkkk.meditation.data.entity

data class AccountDtoModel(
    val name : String,
    val dayCount : Int,
    val achievements : Map<String, String>
) {
    constructor() : this(
        "",
        0,
        hashMapOf()
    )
}
