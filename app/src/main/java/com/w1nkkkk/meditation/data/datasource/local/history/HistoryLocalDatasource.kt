package com.w1nkkkk.meditation.data.datasource.local.history

import com.w1nkkkk.meditation.data.entity.HistoryDboModel

interface HistoryLocalDatasource {
    suspend fun getHistoryList() : List<HistoryDboModel>
    suspend fun addHistoryItem(item : HistoryDboModel)
}