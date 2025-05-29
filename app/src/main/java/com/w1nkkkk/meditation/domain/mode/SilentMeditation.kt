package com.w1nkkkk.meditation.domain.mode

import android.os.Parcel
import android.os.Parcelable
import com.w1nkkkk.meditation.R

/*
Этот режим предназначен для медитации
в полной тишине. Приложение предоставляет только таймер с сигналом начала и конца сессии
 */

class SilentMeditation : MeditationMode(
    name = R.string.silent_meditation,
    description = R.string.silent_meditation_description,
    musicResource = R.raw.silent_meditation,
    image = R.drawable.silent_meditation_ic
) {

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(name)
        dest.writeInt(description)
        dest.writeInt(musicResource)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MeditationMode> = object : Parcelable.Creator<MeditationMode> {
            override fun createFromParcel(parcel: Parcel): MeditationMode {
                return SilentMeditation()
            }

            override fun newArray(size: Int): Array<MeditationMode?> {
                return arrayOfNulls(size)
            }
        }
    }
}