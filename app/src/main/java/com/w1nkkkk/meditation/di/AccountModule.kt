package com.w1nkkkk.meditation.di

import com.w1nkkkk.meditation.data.datasource.remote.account.AccountRemoteDatasource
import com.w1nkkkk.meditation.data.datasource.remote.account.AccountRemoteDatasourceImpl
import com.w1nkkkk.meditation.data.repository.AccountRepositoryImpl
import com.w1nkkkk.meditation.domain.account.AccountRepository
import com.w1nkkkk.meditation.presentation.component.account.AccountViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AccountModule {
    @Provides
    fun provideRemoteDatasource() : AccountRemoteDatasource {
        return AccountRemoteDatasourceImpl()
    }

    @Provides
    fun provideRepository(datasource: AccountRemoteDatasource) : AccountRepository {
        return AccountRepositoryImpl(datasource)
    }

    @Provides
    @Singleton
    fun provideViewModel(repository: AccountRepository) : AccountViewModel {
        return AccountViewModel(repository)
    }
}