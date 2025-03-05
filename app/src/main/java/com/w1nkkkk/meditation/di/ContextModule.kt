package com.w1nkkkk.meditation.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityComponent::class)
object ContextModule {
    @Provides
    fun provideContext(@ApplicationContext appContext: Context) : Context {
        return appContext
    }
}