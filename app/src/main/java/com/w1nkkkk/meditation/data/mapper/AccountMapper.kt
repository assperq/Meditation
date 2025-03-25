package com.w1nkkkk.meditation.data.mapper

import com.w1nkkkk.meditation.data.entity.AccountDtoModel
import com.w1nkkkk.meditation.domain.account.AccountModel

class AccountMapper {
    fun map(account : AccountDtoModel) : AccountModel {
        return AccountModel(
            name = account.name,
            dayCount = account.dayCount,
            achievements = account.achievements
        )
    }
}