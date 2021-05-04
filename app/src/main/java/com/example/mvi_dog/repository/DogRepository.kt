package com.example.mvi_dog.repository

import com.example.mvi_dog.retrofit.DogRetrofit
import com.example.mvi_dog.retrofit.NetworkMapper
import com.example.mvi_dog.room.CacheMapper
import com.example.mvi_dog.room.DogDao
import com.example.mvi_dog.utils.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.UnknownHostException

class DogRepository constructor(
    private val dogDao: DogDao,
    private val dogRetrofit: DogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {
    suspend fun getDogs(): Flow<DataState> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val dogData = dogRetrofit.get()
            val dogMap = networkMapper.mapFromEntityList(dogData)
            for (tempDog in dogMap){
                dogDao.insert(cacheMapper.mapToEntity(tempDog))
            }
            val cacheDog = dogDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cacheDog)))
        }catch (e: Exception){
           when (e){
               is UnknownHostException ->{
                   val cacheDogs = dogDao.get()
                   if (cacheDogs.isEmpty()){
                       emit(DataState.Error(java.lang.Exception("La tabla Dog se encuentra vacia, Conectece a internet ")))
                   }else{
                       emit(DataState.Success(cacheMapper.mapFromEntityList(cacheDogs)))
                   }
               }
            }




           // emit(DataState.Error(e))
        }
    }
}