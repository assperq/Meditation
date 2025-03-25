package com.w1nkkkk.meditation.domain.auth

import com.w1nkkkk.meditation.domain.account.AccountModel

interface AuthRepository {
    suspend fun createUser(email : String, password: String) : AccountModel
    suspend fun singIn(email : String, password: String) : AccountModel
}