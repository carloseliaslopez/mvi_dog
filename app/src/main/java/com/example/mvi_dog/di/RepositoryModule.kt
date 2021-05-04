package com.example.mvi_dog.di

import com.example.mvi_dog.repository.DogRepository
import com.example.mvi_dog.retrofit.DogRetrofit
import com.example.mvi_dog.retrofit.NetworkMapper
import com.example.mvi_dog.room.CacheMapper
import com.example.mvi_dog.room.DogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideCatRepository(
        dogDao: DogDao,
        dogRetrofit: DogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): DogRepository {
        return DogRepository(dogDao, dogRetrofit, cacheMapper, networkMapper)
    }
}