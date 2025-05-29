package com.w1nkkkk.meditation.domain.mode

import android.os.Parcel
import android.os.Parcelable
import com.w1nkkkk.meditation.R

/*
Это медитация, которая направлена на развитие чувства доброты и сострадания
к себе и другим. Приложение воспроизводит голосовую медитацию,
в которой предлагаются мантры или фразы, направленные на развитие любви и сострадания.
 */

class LovingKindnessMeditation() : MeditationMode(
    name = R.string.loving_kindness_meditation,
    description = R.string.loving_kindness_meditation_description,
    musicResource = R.raw.loving_kindness,
    image = R.drawable.loving_kindness_meditation_ic
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
                return LovingKindnessMeditation()
            }

            override fun newArray(size: Int): Array<MeditationMode?> {
                return arrayOfNulls(size)
            }
        }
    }
}