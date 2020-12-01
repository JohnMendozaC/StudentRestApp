package com.lupesoft.studentrestapp.di

import com.lupesoft.studentrestapp.api.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideUserService(): Api = Api.create()
}