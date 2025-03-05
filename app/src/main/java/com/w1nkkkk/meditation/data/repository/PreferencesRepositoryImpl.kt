package com.w1nkkkk.meditation.data.repository

import com.w1nkkkk.meditation.data.datasource.local.preferences.PreferencesLocalDatasource
import com.w1nkkkk.meditation.data.entity.PreferencesModel
import com.w1nkkkk.meditation.data.mapper.PreferencesMapper
import com.w1nkkkk.meditation.domain.preferences.Preferences
import com.w1nkkkk.meditation.domain.preferences.PreferencesRepository
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(
    private val preferencesLocalDatasource : PreferencesLocalDatasource
) : PreferencesRepository {

    val mapper = PreferencesMapper()

    override suspend fun getPreferences(): Preferences {
        return mapper.map(preferencesLocalDatasource.getPreferences())
    }

    override suspend fun setPreferences(preferences: Preferences) {
        preferencesLocalDatasource.setPreferences(mapper.mapToModel(preferences))
    }
}