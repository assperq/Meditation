package com.w1nkkkk.meditation.data.datasource.local.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.w1nkkkk.meditation.data.entity.PreferencesModel
import com.w1nkkkk.meditation.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesLocalDatasourceImpl @Inject constructor(
    private val context: Context
) : PreferencesLocalDatasource {

    override suspend fun getPreferences(): PreferencesModel {
        val timesMili = context.dataStore.data.map { preferences ->
            val medTime = preferences[DataStoreKeys.meditationTime] ?: 0
            val changeDaysCount = preferences[DataStoreKeys.updateDaysCount] ?: false
            val todayDate = preferences[DataStoreKeys.todayDate] ?: 0
            return@map listOf<Any>(medTime, changeDaysCount, todayDate)
        }
        return PreferencesModel(timesMili.first()[0] as Long,
            timesMili.first()[1] as Boolean, timesMili.first()[2] as Long)
    }

    override suspend fun setPreferences(preferences: PreferencesModel) {
        context.dataStore.edit { settings ->
            settings[DataStoreKeys.meditationTime] = preferences.meditationTime
            settings[DataStoreKeys.updateDaysCount] = preferences.updateDaysCount
            settings[DataStoreKeys.todayDate] = preferences.toDayDate
        }
    }
}