package com.w1nkkkk.meditation.data.datasource.remote.auth

interface AuthRemoteDatasource {
    suspend fun createUser(email : String, password: String) : Boolean
    suspend fun singIn(email : String, password: String) : Boolean
}