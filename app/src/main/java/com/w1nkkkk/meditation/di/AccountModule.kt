package com.w1nkkkk.meditation.di

import com.w1nkkkk.meditation.data.datasource.remote.account.AccountRemoteDatasource
import com.w1nkkkk.meditation.data.datasource.remote.account.AccountRemoteDatasourceImpl
import com.w1nkkkk.meditation.data.repository.AccountRepositoryImpl
import com.w1nkkkk.meditation.domain.account.AccountRepository
import com.w1nkkkk.meditation.presentation.component.account.AccountViewModel
import dagger.Provides
import javax.inject.Singleton

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