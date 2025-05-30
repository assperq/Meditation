package com.w1nkkkk.meditation.data.repository

import com.w1nkkkk.meditation.data.datasource.remote.auth.AuthRemoteDatasource
import com.w1nkkkk.meditation.data.mapper.AccountMapper
import com.w1nkkkk.meditation.domain.account.AccountModel
import com.w1nkkkk.meditation.domain.auth.AuthRepository
import jakarta.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val datasource: AuthRemoteDatasource
) : AuthRepository {
    private val mapper : AccountMapper = AccountMapper()

    override suspend fun createUser(email: String, password: String): AccountModel {
        return mapper.map(datasource.createUser(email, password))
    }

    override suspend fun singIn(email: String, password: String): AccountModel {
        return mapper.map(datasource.singIn(email, password))
    }

    override suspend fun signInAdmin(
        login: String,
        password: String
    ): Boolean {
        return datasource.signInAdmin(login, password)
    }
}