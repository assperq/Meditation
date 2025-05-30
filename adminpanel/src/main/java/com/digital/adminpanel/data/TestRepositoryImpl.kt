package com.digital.adminpanel.data

import com.digital.adminpanel.domain.Quiz
import com.digital.adminpanel.domain.TestRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class TestRepositoryImpl : TestRepository {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override suspend fun getTests(): Quiz {
        return try {
            val document = firestore.collection("tests").document("main").get().await()
            if (document.exists()) {
                document.toObject(Quiz::class.java) ?: Quiz()
            } else {
                Quiz()
            }
        } catch (e: Exception) {
            // Логируем ошибку и возвращаем пустой Quiz
            println("Error getting tests: ${e.message}")
            Quiz()
        }
    }

    override suspend fun setTests(quiz: Quiz) {
        try {
            firestore.collection("tests").document("main").set(quiz).await()
        } catch (e: Exception) {
            println("Error saving tests: ${e.message}")
        }
    }
}