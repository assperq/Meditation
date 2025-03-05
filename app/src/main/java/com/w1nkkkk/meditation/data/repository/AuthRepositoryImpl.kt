package com.w1nkkkk.meditation.data.repository

import com.w1nkkkk.meditation.data.datasource.remote.auth.AuthRemoteDatasource
import com.w1nkkkk.meditation.domain.auth.AuthRepository
import jakarta.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val datasource: AuthRemoteDatasource
) : AuthRepository {
    override suspend fun createUser(email: String, password: String): Boolean {
        return datasource.createUser(email, password)
    }

    override suspend fun singIn(email: String, password: String): Boolean {
        return datasource.singIn(email, password)
    }


}