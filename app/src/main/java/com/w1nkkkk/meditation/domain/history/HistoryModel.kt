package com.w1nkkkk.meditation.domain.history

import kotlinx.datetime.LocalDate

data class HistoryModel(
    val date : LocalDate,
    val type : String,
    val count : String
)
