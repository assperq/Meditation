package com.w1nkkkk.meditation.domain.mode

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.runtime.Composable
import com.w1nkkkk.meditation.R

/*
Это медитация, основанная на работе с воображением.
Приложение воспроизводит голосовые инструкции и подходящий звуковой фон,
помогая пользователю создавать в своем воображении определенные образы
 */

class Visualization : MeditationMode(
    name = R.string.visualization,
    description = R.string.visualization_description,
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
                return Visualization()
            }

            override fun newArray(size: Int): Array<MeditationMode?> {
                return arrayOfNulls(size)
            }
        }
    }
}