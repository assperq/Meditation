package com.w1nkkkk.meditation.domain.mode

import android.content.Context
import android.os.Parcelable


// абстракьный класс для режимов медитации, он описывает их работу

abstract class MeditationMode(
    protected val name : Int,
    protected val description: Int,
    val musicResource : Int,
    val image : Int
) : Parcelable {
    fun getName(context: Context) = context.getString(name)
    fun getDescription(context: Context) = context.getString(description)

    companion object {
        val parcelableName = "MeditationMode"
    }
}