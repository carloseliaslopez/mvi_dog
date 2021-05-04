package com.example.mvi_dog.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApp : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}