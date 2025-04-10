package com.w1nkkkk.meditation.data.repository

import com.w1nkkkk.meditation.data.datasource.local.history.HistoryLocalDatasource
import com.w1nkkkk.meditation.data.mapper.HistoryMapper
import com.w1nkkkk.meditation.domain.history.HistoryModel
import com.w1nkkkk.meditation.domain.history.HistoryRepository

class HistoryRepositoryImpl(
    private val datasource: HistoryLocalDatasource
) : HistoryRepository {

    private val mapper = HistoryMapper()

    override suspend fun getHistoryList(): List<HistoryModel> {
        return datasource.getHistoryList().map { mapper.map(it) }
    }

    override suspend fun addHistoryItem(item: HistoryModel) {
        return datasource.addHistoryItem(mapper.mapToDboModel(item))
    }

}