package com.w1nkkkk.meditation.data.repository

import com.w1nkkkk.meditation.data.datasource.remote.account.AccountRemoteDatasource
import com.w1nkkkk.meditation.data.mapper.AccountMapper
import com.w1nkkkk.meditation.domain.account.AccountModel
import com.w1nkkkk.meditation.domain.account.AccountRepository

class AccountRepositoryImpl(
    private val datasourceImpl: AccountRemoteDatasource
) : AccountRepository {

    val mapper = AccountMapper()

    override suspend fun changeName(name: String) {
        datasourceImpl.changeName(name)
    }

    override suspend fun changeDaysCount(date: Long) {
        datasourceImpl.changeDaysCount(date)
    }

    override suspend fun addAchievement(achievement: Map<String, String>) {
        datasourceImpl.addAchievement(achievement)
    }

    override suspend fun changeEmotionalState(state: Int) {
        datasourceImpl.changeEmotionalState(state)
    }

    override suspend fun getAccountData(id: String): AccountModel {
        return mapper.map(datasourceImpl.getAccountData(id))
    }
}