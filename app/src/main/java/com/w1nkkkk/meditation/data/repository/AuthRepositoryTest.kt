package com.w1nkkkk.meditation.data.repository

import com.w1nkkkk.meditation.domain.auth.AuthRepository

class AuthRepositoryTest : AuthRepository {
    override suspend fun createUser(email: String, password: String): Boolean {
        return true
    }

    override suspend fun singIn(email: String, password: String): Boolean {
        return true
    }
}