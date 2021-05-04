package com.example.mvi_dog.utils

import com.example.mvi_dog.model.Dog
import java.lang.Exception

sealed class DataState {
    object Idle: DataState()
    data class Success(val dogs: List<Dog>) : DataState()
    data class Error(val exception: Exception) : DataState()
    object Loading: DataState()
}