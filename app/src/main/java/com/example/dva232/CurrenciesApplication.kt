package com.example.dva232

import android.app.Application
import com.example.dva232.viewModel.AppContainer
import com.example.dva232.viewModel.DefaultAppContainer

class CurrenciesApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}