package com.w1nkkkk.meditation.data.datasource.local.history

import android.content.Context
import com.w1nkkkk.meditation.data.entity.HistoryDboModel
import jakarta.inject.Inject

class HistoryLocalDatasourceImpl @Inject constructor(
    context : Context
) : HistoryLocalDatasource {

    private val db = HistoryDatabase.getInstance(context)

    override suspend fun getHistoryList(): List<HistoryDboModel> {
        return db.getHistoryDao().getHistoryList()
    }

    override suspend fun addHistoryItem(item: HistoryDboModel) {
        return db.getHistoryDao().insertHistoryItem(item)
    }
}