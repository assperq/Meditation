package com.w1nkkkk.meditation.domain.account

data class AccountModel(
    val name : String,
    val dayCount : Int,
    val achievements : Map<String, String>,
    val emotion_state : Int
) {
    constructor() : this("", -1, mapOf(), 0)

    override fun equals(other: Any?): Boolean {
        val otherAccount = other as AccountModel
        return this.hashCode() == otherAccount.hashCode()
    }

    override fun hashCode(): Int {
        return name.hashCode() + dayCount + emotion_state
    }
}

