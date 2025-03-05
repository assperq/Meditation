package com.w1nkkkk.meditation.data.mapper

import android.util.Log
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import com.w1nkkkk.meditation.data.entity.PreferencesModel
import com.w1nkkkk.meditation.domain.preferences.Preferences

class PreferencesMapper {
    fun map(preferencesModel: PreferencesModel) : Preferences {
        return Preferences(
            mutableFloatStateOf(preferencesModel.meditationTime.toFloat())
        )
    }

    fun mapToModel(preferences: Preferences) : PreferencesModel {
        return PreferencesModel(
            preferences.meditaitionTime.value.toLong()
        )
    }
}