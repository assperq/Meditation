package com.w1nkkkk.meditation.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class HistoryDboModel(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    val date : String,
    val type : String,
    val count : String
)
