package com.w1nkkkk.meditation.presentation.component

import android.icu.text.SimpleDateFormat
import java.util.Date

object DateObject {
    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm")
        return format.format(date)
    }
    fun currentTimeToLong(): Long {
        return System.currentTimeMillis()
    }
    fun convertDateToLong(date: String): Long {
        val df = SimpleDateFormat("yyyy.MM.dd HH:mm")
        return df.parse(date).time
    }
}