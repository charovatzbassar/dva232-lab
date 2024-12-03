package com.example.dva232.viewModel.repos

import com.example.dva232.viewModel.services.FixerService


interface FixerRepository {
    suspend fun getCurrencies(): String
}


class NetworkFixerRepository(
    private val fixerService: FixerService
) : FixerRepository {
    override suspend fun getCurrencies(): String = fixerService.getCurrencies()
}