package com.w1nkkkk.meditation.data.datasource.remote.account

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.w1nkkkk.meditation.data.datasource.remote.FirestoreCollections
import com.w1nkkkk.meditation.data.datasource.remote.FirestoreFields
import com.w1nkkkk.meditation.data.entity.AccountDtoModel
import kotlinx.coroutines.tasks.await

class AccountRemoteDatasourceImpl : AccountRemoteDatasource {

    private val db = FirebaseFirestore.getInstance().collection(FirestoreCollections.users)

    override suspend fun changeName(name: String) {
        val map = mapOf(FirestoreFields.name to name)
        db.document(FirebaseAuth.getInstance().uid ?: "null").update(map)
    }

    override suspend fun changeDaysCount(date: Long) {
        val map = mapOf(FirestoreFields.dayCount to date)
        db.document(FirebaseAuth.getInstance().uid ?: "null").update(map)
    }

    override suspend fun addAchievement(achievement: Map<String, String>) {
        val documentSnapshot = db.document(FirebaseAuth.getInstance().uid ?: "null").get().await()
        val comments = documentSnapshot.get(FirestoreFields.achievements) as? List<Map<String, String>> ?: emptyList()
        val updatedComments = comments.toMutableList().add(achievement)
        db.document(FirebaseAuth.getInstance().uid ?: "null").update(FirestoreFields.achievements, updatedComments)
    }

    override suspend fun changeEmotionalState(state: Int) {
        val map = mapOf(FirestoreFields.emotionState to state)
        db.document(FirebaseAuth.getInstance().uid ?: "null").update(map)
    }

    override suspend fun getAccountData(id: String): AccountDtoModel {
        return db.document(id).get().await().toObject(AccountDtoModel::class.java) ?: AccountDtoModel()
    }
}