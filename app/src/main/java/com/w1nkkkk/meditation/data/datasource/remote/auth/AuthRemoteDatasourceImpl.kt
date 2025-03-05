package com.w1nkkkk.meditation.data.datasource.remote.auth

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRemoteDatasourceImpl : AuthRemoteDatasource {

    private val auth = FirebaseAuth.getInstance()

    override suspend fun createUser(email: String, password: String): Boolean {
        val res = auth.createUserWithEmailAndPassword(email, password).await()
        return res.user != null
    }

    override suspend fun singIn(email: String, password: String): Boolean {
        val res = auth.signInWithEmailAndPassword(email, password).await()
        return res.user != null
    }
}