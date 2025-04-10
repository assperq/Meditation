package com.w1nkkkk.meditation.data.mapper

import com.w1nkkkk.meditation.data.entity.HistoryDboModel
import com.w1nkkkk.meditation.domain.history.HistoryModel
import com.w1nkkkk.meditation.toFormattedString
import com.w1nkkkk.meditation.toLocalDate

class HistoryMapper {
    fun map(item : HistoryDboModel) : HistoryModel {
        return HistoryModel(
            date = item.date.toLocalDate(),
            type = item.type,
            item.count
        )
    }

    fun mapToDboModel(item : HistoryModel) : HistoryDboModel {
        return HistoryDboModel(
            date = item.date.toFormattedString(),
            type = item.type,
            count = item.count
        )
    }
}