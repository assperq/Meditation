package com.w1nkkkk.meditation.data.datasource.remote.auth

import com.w1nkkkk.meditation.data.entity.AccountDtoModel

interface AuthRemoteDatasource {
    suspend fun createUser(email : String, password: String) : AccountDtoModel
    suspend fun singIn(email : String, password: String) : AccountDtoModel
}