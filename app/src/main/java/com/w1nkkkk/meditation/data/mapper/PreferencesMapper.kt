package com.w1nkkkk.meditation.data.mapper

import androidx.compose.runtime.mutableFloatStateOf
import com.w1nkkkk.meditation.data.entity.PreferencesModel
import com.w1nkkkk.meditation.domain.preferences.Preferences
import com.w1nkkkk.meditation.presentation.component.DateObject

class PreferencesMapper {
    fun map(preferencesModel: PreferencesModel) : Preferences {
        return Preferences(
            mutableFloatStateOf(preferencesModel.meditationTime.toFloat()),
            preferencesModel.updateDaysCount,
            DateObject.convertLongToTime(preferencesModel.toDayDate)
        )
    }

    fun mapToModel(preferences: Preferences) : PreferencesModel {
        return PreferencesModel(
            preferences.meditaitionTime.value.toLong(),
            preferences.updateDaysCount,
            DateObject.convertDateToLong(preferences.toDayDate)
        )
    }
}