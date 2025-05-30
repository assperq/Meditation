package com.w1nkkkk.meditation.di

import com.digital.adminpanel.data.TestRepositoryImpl
import com.digital.adminpanel.domain.TestRepository
import com.digital.adminpanel.presentation.TestsViewModel
import com.w1nkkkk.meditation.presentation.tests.UserTestViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestsModule {

    @Provides
    @Singleton
    fun provideRepository() : TestRepository {
        return TestRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideViewModel(repository: TestRepository) : TestsViewModel {
        return TestsViewModel(repository)
    }

    @Provides
    @Singleton
    fun provideTestViewModel(repository: TestRepository) : UserTestViewModel {
        return UserTestViewModel(repository)
    }
}