package com.w1nkkkk.meditation.domain.account

interface AccountRepository {
    suspend fun changeName(name: String)
    suspend fun changeDaysCount(date: Long)
    suspend fun addAchievement(achievement: Map<String, String>)
    suspend fun changeEmotionalState(state : Int)
    suspend fun getAccountData(id: String) : AccountModel
}