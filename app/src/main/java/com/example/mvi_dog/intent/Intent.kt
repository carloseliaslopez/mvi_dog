package com.example.mvi_dog.intent

sealed class Intent {
    object GetDogEvent: Intent()
    object None: Intent()
}