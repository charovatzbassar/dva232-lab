package com.example.dva232.viewModel


import com.example.dva232.view.util.Constants
import com.example.dva232.viewModel.repos.FixerRepository
import com.example.dva232.viewModel.repos.NetworkFixerRepository
import com.example.dva232.viewModel.services.FixerService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory


interface AppContainer {
    val fixerRepository: FixerRepository
}


class DefaultAppContainer : AppContainer {
    private val json = Json { ignoreUnknownKeys = true }

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(Constants.BASE_URL)
        .build()


    private val fixerService: FixerService by lazy {
        retrofit.create(FixerService::class.java)
    }


    override val fixerRepository: FixerRepository by lazy {
        NetworkFixerRepository(fixerService)
    }
}
