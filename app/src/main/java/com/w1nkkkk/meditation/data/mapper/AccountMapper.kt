package com.w1nkkkk.meditation.data.mapper

import com.w1nkkkk.meditation.data.entity.AccountDtoModel
import com.w1nkkkk.meditation.domain.account.AccountModel

class AccountMapper {
    fun map(account : AccountDtoModel) : AccountModel {
        return AccountModel(
            name = account.name,
            dayCount = account.day_count,
            achievements = account.achievements,
            emotion_state = account.emotion_state
        )
    }
}