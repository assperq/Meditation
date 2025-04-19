package com.w1nkkkk.meditation.domain.mode

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.runtime.Composable
import com.w1nkkkk.meditation.R

/*
Этот режим сфокусирован на прослушивании звуков природы.
Приложение предлагает пользователю различные звуки природы
(шум дождя, пение птиц, шум моря, потрескивание костра) или их комбинации.
Пользователь может выбрать длительность медитации
 */

class NatureSoundMeditation : MeditationMode(
    name = R.string.nature_sound_meditation,
    description = R.string.nature_sound_meditation_description,
    musicResource = R.raw.test_music
) {
    override fun meditate() {}

    @Composable
    override fun MeditationUI() {}

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
                return NatureSoundMeditation()
            }

            override fun newArray(size: Int): Array<MeditationMode?> {
                return arrayOfNulls(size)
            }
        }
    }
}