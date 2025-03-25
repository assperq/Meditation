package com.w1nkkkk.meditation.data.mapper

import com.w1nkkkk.meditation.data.entity.AccountDtoModel
import com.w1nkkkk.meditation.domain.account.AccountModel

class AccountModelMapper {
    fun map(accountDtoModel: AccountDtoModel) : AccountModel {
        return AccountModel(
            accountDtoModel.name,
            accountDtoModel.dayCount,
            accountDtoModel.achievements
        )
    }
}