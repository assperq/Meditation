package com.digital.adminpanel.domain

interface TestRepository {
    suspend fun getTests() : Quiz
    suspend fun setTests(quiz: Quiz)
}