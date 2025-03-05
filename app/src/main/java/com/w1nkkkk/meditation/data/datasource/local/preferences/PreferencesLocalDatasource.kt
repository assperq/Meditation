package com.w1nkkkk.meditation.data.datasource.local.preferences

import com.w1nkkkk.meditation.data.entity.PreferencesModel

interface PreferencesLocalDatasource {
    suspend fun getPreferences() : PreferencesModel

    suspend fun setPreferences(preferences: PreferencesModel)
}