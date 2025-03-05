package com.w1nkkkk.meditation.domain.auth

interface AuthRepository {
    suspend fun createUser(email : String, password: String) : Boolean
    suspend fun singIn(email : String, password: String) : Boolean
}