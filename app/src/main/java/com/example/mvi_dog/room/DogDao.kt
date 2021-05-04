package com.example.mvi_dog.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dogEntity: DogCacheEntity): Long
    @Query("select * from perrito")
    suspend fun get(): List<DogCacheEntity>
}