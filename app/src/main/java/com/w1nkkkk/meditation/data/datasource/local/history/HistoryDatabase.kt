package com.w1nkkkk.meditation.data.datasource.local.history

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.w1nkkkk.meditation.data.entity.HistoryDboModel

@Database(entities = [HistoryDboModel::class], version = 1)
abstract class HistoryDatabase : RoomDatabase() {

    abstract fun getHistoryDao() : HistoryDao

    companion object {
        private var INSTANCE : HistoryDatabase? = null

        fun getInstance(context: Context) : HistoryDatabase {
            return INSTANCE ?: synchronized(this) {
                val inst = Room.databaseBuilder(
                    context,
                    HistoryDatabase::class.java,
                    "history_database"
                ).build()
                INSTANCE = inst
                inst
            }
        }
    }
}