package com.w1nkkkk.meditation

import android.app.Application
import android.content.Context
import com.w1nkkkk.meditation.domain.AppComponent
import com.w1nkkkk.meditation.domain.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}

val Context.appComponent : AppComponent
    get() = when(this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }