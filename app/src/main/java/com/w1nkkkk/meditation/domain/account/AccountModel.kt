package com.w1nkkkk.meditation.domain.account

data class AccountModel(
    val name : String,
    val dayCount : Int,
    val achievements : Map<String, String>
) {
    constructor() : this("", -1, mapOf())
}

