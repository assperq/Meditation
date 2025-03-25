package com.w1nkkkk.meditation.data.datasource.local.preferences

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey

object DataStoreKeys {
    val meditationTime = longPreferencesKey("meditation_time")
    val updateDaysCount = booleanPreferencesKey("update_days_count")
    val todayDate = longPreferencesKey("today_date")
}