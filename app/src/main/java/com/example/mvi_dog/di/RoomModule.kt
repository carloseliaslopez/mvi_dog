package com.example.mvi_dog.di

import android.content.Context
import androidx.room.Room
import com.example.mvi_dog.room.DogDao
import com.example.mvi_dog.room.DogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideDogDb(@ApplicationContext context: Context): DogDatabase {
        return Room
            .databaseBuilder(context, DogDatabase::class.java, DogDatabase.DATABASE_NAME).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideDogDao(dogDatabase: DogDatabase): DogDao {
        return dogDatabase.dogDao()
    }
}