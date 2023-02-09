package com.example.movieproject.di

import com.example.movieproject.data.api.ServiceInterface
import com.example.movieproject.data.repository.popular.PopularRepository
import com.example.movieproject.data.repository.popular.PopularRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAgentsRepository(serviceInterface: ServiceInterface): PopularRepository {
        return PopularRepositoryImpl(serviceInterface)
    }

}