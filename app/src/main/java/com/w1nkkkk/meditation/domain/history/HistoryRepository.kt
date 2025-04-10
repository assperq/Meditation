package com.w1nkkkk.meditation.domain.history

interface HistoryRepository {
    suspend fun getHistoryList() : List<HistoryModel>
    suspend fun addHistoryItem(item : HistoryModel)
}