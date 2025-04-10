package com.w1nkkkk.meditation.di

import android.content.Context
import com.w1nkkkk.meditation.data.datasource.local.history.HistoryLocalDatasource
import com.w1nkkkk.meditation.data.datasource.local.history.HistoryLocalDatasourceImpl
import com.w1nkkkk.meditation.data.repository.HistoryRepositoryImpl
import com.w1nkkkk.meditation.domain.history.HistoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HistoryModule {
    @Provides
    fun provideHistoryLocalDatasource(context: Context) : HistoryLocalDatasource {
        return HistoryLocalDatasourceImpl(context)
    }

    @Provides
    fun provideHistoryRepository(datasource: HistoryLocalDatasource) : HistoryRepository {
        return HistoryRepositoryImpl(datasource)
    }
}