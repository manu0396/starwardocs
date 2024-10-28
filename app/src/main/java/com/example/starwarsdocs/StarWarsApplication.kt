package com.example.starwarsdocs

import android.app.Application
import com.example.starwarsdocs.di.AppComponent
import com.example.starwarsdocs.di.DaggerAppComponent
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class StarWarsApplication: Application() {
    private lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().inject(this).build()
    }
}