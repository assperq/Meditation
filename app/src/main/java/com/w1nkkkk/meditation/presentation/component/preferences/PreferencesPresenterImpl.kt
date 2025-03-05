package com.w1nkkkk.meditation.presentation.component.preferences

import com.w1nkkkk.meditation.domain.preferences.Preferences
import com.w1nkkkk.meditation.domain.preferences.PreferencesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PreferencesPresenterImpl @Inject constructor(
    private val repository: PreferencesRepository,
    private val view : PreferencesView
) : PreferencesPresenter {

    override fun getPreferences() {
        CoroutineScope(Dispatchers.IO).launch {
            view.onUpdatePreferences(repository.getPreferences())
        }
    }

    override fun setPreferences(preferences: Preferences) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.setPreferences(preferences)
            getPreferences()
        }
    }
}