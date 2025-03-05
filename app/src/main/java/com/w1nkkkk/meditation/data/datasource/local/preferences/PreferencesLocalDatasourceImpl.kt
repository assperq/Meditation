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
            preferences[DataStoreKeys.meditationTime] ?: 0
        }
        return PreferencesModel(timesMili.first())
    }

    override suspend fun setPreferences(preferences: PreferencesModel) {
        context.dataStore.edit { settings ->
            settings[DataStoreKeys.meditationTime] = preferences.meditationTime
        }
    }
}