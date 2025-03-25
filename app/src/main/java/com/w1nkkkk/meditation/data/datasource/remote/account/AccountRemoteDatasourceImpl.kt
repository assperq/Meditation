package com.w1nkkkk.meditation.data.datasource.remote.account

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.w1nkkkk.meditation.data.datasource.remote.FirestoreCollections
import com.w1nkkkk.meditation.data.entity.AccountDtoModel
import kotlinx.coroutines.tasks.await

class AccountRemoteDatasourceImpl : AccountRemoteDatasource {

    private object Fields {
        val name = "name"
        val dayCount = "day_count"
        val achievements = "achievements"
    }

    private val db = FirebaseFirestore.getInstance().collection(FirestoreCollections.users)

    override suspend fun changeName(name: String) {
        val map = mapOf(Fields.name to name)
        db.document(FirebaseAuth.getInstance().uid ?: "null").update(map)
    }

    override suspend fun changeDaysCount(date: Long) {
        val map = mapOf(Fields.dayCount to date)
        db.document(FirebaseAuth.getInstance().uid ?: "null").update(map)
    }

    override suspend fun addAchievement(achievement: Map<String, String>) {
        val documentSnapshot = db.document(FirebaseAuth.getInstance().uid ?: "null").get().await()
        val comments = documentSnapshot.get(Fields.achievements) as? List<Map<String, String>> ?: emptyList()
        val updatedComments = comments.toMutableList().add(achievement)
        db.document(FirebaseAuth.getInstance().uid ?: "null").update(Fields.achievements, updatedComments)
    }

    override suspend fun getAccountData(id: String): AccountDtoModel {
        return db.document(id).get().await().toObject(AccountDtoModel::class.java) ?: AccountDtoModel()
    }
}