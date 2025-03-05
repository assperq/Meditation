package com.w1nkkkk.meditation.domain.preferences

import com.w1nkkkk.meditation.data.entity.PreferencesModel

interface PreferencesRepository {
    suspend fun getPreferences(): Preferences
    suspend fun setPreferences(preferences: Preferences)
}