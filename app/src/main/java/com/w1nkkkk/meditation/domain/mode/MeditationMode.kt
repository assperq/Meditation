package com.w1nkkkk.meditation.domain.mode

import android.content.ClipDescription
import android.content.Context
import androidx.compose.runtime.Composable

abstract class MeditationMode(protected val name : Int, protected val description: Int) {
    fun getName(context: Context) = context.getString(name)
    fun getDescription(context: Context) = context.getString(description)

    abstract fun meditate()

    @Composable
    abstract fun MeditationUI()
}