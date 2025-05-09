package com.w1nkkkk.meditation

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import androidx.core.content.ContextCompat.getSystemServiceName
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.HiltAndroidApp
import kotlinx.datetime.LocalDate

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@HiltAndroidApp
class App : Application() {}

fun String.toLocalDate(): LocalDate {
    return LocalDate.parse(this) // "2025-04-10" (ISO-формат)
}

fun LocalDate.toFormattedString(): String {
    return this.toString() // "2025-04-10" (ISO-формат)
}

fun isMyServiceRunning(serviceClass : Class<*>, context: Context) : Boolean {
    val manager : ActivityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager;
    for (service in manager.getRunningServices(Integer.MAX_VALUE)) {
        if (getSystemServiceName(context, serviceClass).equals(service.service.className)) {
            return true;
        }
    }
    return false;
}