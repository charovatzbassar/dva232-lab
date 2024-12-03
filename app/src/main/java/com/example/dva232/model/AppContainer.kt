package com.example.dva232.model


import com.example.dva232.view.util.Constants
import com.example.dva232.viewModel.repos.FixerRepository
import com.example.dva232.viewModel.repos.NetworkFixerRepository
import com.example.dva232.viewModel.services.FixerService
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * Dependency Injection container at the application level.
 */
interface AppContainer {
    val fixerRepository: FixerRepository
}

/**
 * Implementation for the Dependency Injection container at the application level.
 *
 * Variables are initialized lazily and the same instance is shared across the whole app.
 */
class DefaultAppContainer : AppContainer {

    /**
     * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
     */
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .build()

    /**
     * Retrofit service object for creating api calls
     */
    private val fixerService: FixerService by lazy {
        retrofit.create(FixerService::class.java)
    }

    /**
     * DI implementation for Mars photos repository
     */
    override val fixerRepository: FixerRepository by lazy {
        NetworkFixerRepository(fixerService)
    }
}
