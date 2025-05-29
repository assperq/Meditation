package com.w1nkkkk.meditation.domain.mode

import android.os.Parcel
import android.os.Parcelable
import com.w1nkkkk.meditation.R

/*
Это базовый режим, который помогает
сосредоточиться на текущем моменте через наблюдение за дыханием.
Пользователю предлагается таймер, который можно настроить (например, 5, 10, 15 минут).
Приложение может предлагать тихий звук колокольчика в начале и конце сессии. В процессе
медитации может звучать очень тихий, нежный звуковой фон,
имитирующий шум природы или спокойную музыку.
 */

class MindfulBreathing : MeditationMode(
    name = R.string.mindful_breathing,
    description = R.string.mindful_breathing_description,
    musicResource = R.raw.mindful_breathing,
    image = R.drawable.mindful_breathing_ic
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
                return MindfulBreathing()
            }

            override fun newArray(size: Int): Array<MeditationMode?> {
                return arrayOfNulls(size)
            }
        }
    }

}