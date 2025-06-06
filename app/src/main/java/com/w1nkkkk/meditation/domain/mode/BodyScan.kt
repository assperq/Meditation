package com.w1nkkkk.meditation.domain.mode

import android.os.Parcel
import android.os.Parcelable
import com.w1nkkkk.meditation.R

/*
Это guided-медитация, которая ведет пользователя через последовательное
осознание различных частей тела. Приложение будет воспроизводить голос,
который дает инструкции по сканированию тела сверху вниз (например, от макушки головы
до кончиков пальцев ног) или наоборот.
 */

class BodyScan : MeditationMode(
    name = R.string.body_scan,
    description = R.string.body_scan_description,
    musicResource = R.raw.body_scan,
    image = R.drawable.body_scan_ic
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
                return BodyScan()
            }

            override fun newArray(size: Int): Array<MeditationMode?> {
                return arrayOfNulls(size)
            }
        }
    }
}