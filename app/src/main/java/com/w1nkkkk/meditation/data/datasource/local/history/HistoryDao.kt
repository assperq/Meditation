package com.w1nkkkk.meditation.data.datasource.local.history

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.w1nkkkk.meditation.data.entity.HistoryDboModel

@Dao
interface HistoryDao {
    @Insert
    fun insertHistoryItem(item : HistoryDboModel)

    @Query("SELECT * FROM history ORDER BY id DESC")
    fun getHistoryList() : List<HistoryDboModel>
}