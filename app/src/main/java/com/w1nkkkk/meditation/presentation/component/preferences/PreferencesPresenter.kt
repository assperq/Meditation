package com.w1nkkkk.meditation.presentation.component.preferences

import com.w1nkkkk.meditation.domain.preferences.Preferences

interface PreferencesPresenter {
    fun getPreferences()
    fun setPreferences(preferences: Preferences)
}