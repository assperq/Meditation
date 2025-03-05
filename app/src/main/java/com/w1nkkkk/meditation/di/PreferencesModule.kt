package com.w1nkkkk.meditation.di

import android.content.Context
import com.w1nkkkk.meditation.data.datasource.local.preferences.PreferencesLocalDatasource
import com.w1nkkkk.meditation.data.datasource.local.preferences.PreferencesLocalDatasourceImpl
import com.w1nkkkk.meditation.data.repository.PreferencesRepositoryImpl
import com.w1nkkkk.meditation.domain.preferences.PreferencesRepository
import com.w1nkkkk.meditation.presentation.MainActivity
import com.w1nkkkk.meditation.presentation.component.preferences.PreferencesPresenter
import com.w1nkkkk.meditation.presentation.component.preferences.PreferencesPresenterImpl
import com.w1nkkkk.meditation.presentation.component.preferences.PreferencesView
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
object PreferencesModule {
    @Provides
    fun providePreferencesLocalDatasource(context: Context) : PreferencesLocalDatasource {
        return PreferencesLocalDatasourceImpl(context)
    }

    @Provides
    fun providePreferencesRepository(datasource : PreferencesLocalDatasource) : PreferencesRepository {
        return PreferencesRepositoryImpl(datasource)
    }

    @Provides
    fun providePreferencesPresenter(
        repository: PreferencesRepository,
        @ActivityContext activity : Context
    ) : PreferencesPresenter {
        val myActivity = if(activity is MainActivity) activity
        else throw Exception("NOT MAIN ACTIVITY PROVIDE")
        return PreferencesPresenterImpl(repository, myActivity)
    }
}