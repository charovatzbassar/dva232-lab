package com.example.dva232.viewModel.services

import com.example.dva232.BuildConfig
import com.example.dva232.view.util.FixerResponse
import retrofit2.http.GET

interface FixerService {
    @GET("latest?access_key=${BuildConfig.API_KEY}")
    suspend fun getCurrencies(): FixerResponse
}