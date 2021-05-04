package com.example.mvi_dog.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DogCacheEntity::class], version = 1)
abstract class DogDatabase : RoomDatabase() {
    companion object{
        val DATABASE_NAME = "DogDB"
    }
    abstract fun dogDao(): DogDao
}