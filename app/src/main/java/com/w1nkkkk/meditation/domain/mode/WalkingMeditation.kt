package com.w1nkkkk.meditation.domain.mode

import android.os.Parcel
import android.os.Parcelable
import com.w1nkkkk.meditation.R

/*
Этот режим предназначен для медитации во время ходьбы.
Приложение может предложить тихий звуковой фон,
подходящий для прогулки, и будет озвучивать инструкции о том, как осознанно двигаться.
 */

class WalkingMeditation : MeditationMode(
    name = R.string.walking_meditation,
    description = R.string.walking_meditation_description,
    musicResource = R.raw.walking,
    image = R.drawable.walking_meditation_ic
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
                return WalkingMeditation()
            }

            override fun newArray(size: Int): Array<MeditationMode?> {
                return arrayOfNulls(size)
            }
        }
    }
}