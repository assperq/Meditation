package com.w1nkkkk.meditation.presentation.component.preferences

import com.w1nkkkk.meditation.data.entity.PreferencesModel
import com.w1nkkkk.meditation.domain.preferences.Preferences

interface PreferencesView {
    fun onUpdatePreferences(model : Preferences)
}