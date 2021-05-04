package com.example.mvi_dog.retrofit

import retrofit2.http.GET

interface DogRetrofit {
    @GET("search")
    suspend fun get(): List<DogNetworkEntity>
}