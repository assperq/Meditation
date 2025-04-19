package com.w1nkkkk.meditation.domain.mode

import android.content.Context
import android.os.Parcelable
import androidx.compose.runtime.Composable


// абстракьный класс для режимов медитации, он описывает их работу

abstract class MeditationMode(
    protected val name : Int,
    protected val description: Int,
    val musicResource : Int,
) : Parcelable {
    fun getName(context: Context) = context.getString(name)
    fun getDescription(context: Context) = context.getString(description)

    abstract fun meditate()

    @Composable
    abstract fun MeditationUI()

    companion object {
        val parcelableName = "MeditationMode"
    }
}