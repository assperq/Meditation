package com.w1nkkkk.meditation.domain

import com.w1nkkkk.meditation.presentation.MainActivity
import dagger.Component

@Component()
interface AppComponent {
    fun inject(activity: MainActivity)
}