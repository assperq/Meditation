package com.w1nkkkk.meditation.data.datasource.remote.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.w1nkkkk.meditation.data.datasource.remote.DocumentFormer
import com.w1nkkkk.meditation.data.datasource.remote.FirestoreCollections
import com.w1nkkkk.meditation.data.datasource.remote.account.AccountRemoteDatasource
import com.w1nkkkk.meditation.data.datasource.remote.account.AccountRemoteDatasourceImpl
import com.w1nkkkk.meditation.data.entity.AccountDtoModel
import kotlinx.coroutines.tasks.await

class AuthRemoteDatasourceImpl : AuthRemoteDatasource {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance().collection(FirestoreCollections.users)
    private val datasource : AccountRemoteDatasource = AccountRemoteDatasourceImpl()

    override suspend fun createUser(email: String, password: String): AccountDtoModel {
        val res = auth.createUserWithEmailAndPassword(email, password).await()
        db.document(res.user?.uid.toString()).set(DocumentFormer.formUserDocument(
            email.subSequence(0, 5).toString()
        )).await()
        return datasource.getAccountData(res.user?.uid.toString())
    }

    override suspend fun singIn(email: String, password: String): AccountDtoModel {
        val res = auth.signInWithEmailAndPassword(email, password).await()
        return datasource.getAccountData(res.user?.uid.toString())
    }
}