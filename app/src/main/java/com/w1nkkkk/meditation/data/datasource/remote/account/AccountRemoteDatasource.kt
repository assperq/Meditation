package com.w1nkkkk.meditation.data.datasource.remote.account

import com.w1nkkkk.meditation.data.entity.AccountDtoModel

interface AccountRemoteDatasource {
    suspend fun changeName(name: String)
    suspend fun changeDaysCount(date: Long)
    suspend fun addAchievement(achievement: Map<String, String>)
    suspend fun getAccountData(id: String) : AccountDtoModel
}