package com.example.dva232.viewModel.repos

import com.example.dva232.view.util.FixerResponse
import com.example.dva232.viewModel.services.FixerService


interface FixerRepository {
    suspend fun getCurrencies(): FixerResponse
}


class NetworkFixerRepository(
    private val fixerService: FixerService
) : FixerRepository {
    override suspend fun getCurrencies(): FixerResponse = fixerService.getCurrencies()
}