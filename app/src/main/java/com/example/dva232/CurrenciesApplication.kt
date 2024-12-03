package com.example.dva232

import android.app.Application
import com.example.dva232.model.AppContainer
import com.example.dva232.model.DefaultAppContainer

class CurrenciesApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}