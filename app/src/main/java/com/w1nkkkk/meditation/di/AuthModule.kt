package com.w1nkkkk.meditation.di

import com.w1nkkkk.meditation.data.datasource.remote.auth.AuthRemoteDatasource
import com.w1nkkkk.meditation.data.datasource.remote.auth.AuthRemoteDatasourceImpl
import com.w1nkkkk.meditation.data.repository.AuthRepositoryImpl
import com.w1nkkkk.meditation.domain.auth.AuthRepository
import com.w1nkkkk.meditation.presentation.component.auth.AuthViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    fun provideRemoteDatasource() : AuthRemoteDatasource {
        return AuthRemoteDatasourceImpl()
    }

    @Provides
    fun provideRepository(datasource: AuthRemoteDatasource) : AuthRepository {
        return AuthRepositoryImpl(datasource)
    }

    @Provides
    @Singleton
    fun provideViewModel(repository: AuthRepository) : AuthViewModel {
        return AuthViewModel(repository)
    }
}