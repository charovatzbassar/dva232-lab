package com.example.dva232.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.dva232.CurrenciesApplication
import com.example.dva232.view.util.FixerResponse
import com.example.dva232.viewModel.repos.FixerRepository
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okio.IOException
import retrofit2.HttpException

class AppViewModel(private val fixerRepository: FixerRepository) : ViewModel() {
    init {
        getCurrencies()
    }


    fun getCurrencies() {
        viewModelScope.launch {
            try {
                val data: String = fixerRepository.getCurrencies()
                val userList = Json.decodeFromString<FixerResponse>(data)


                println(userList)
            }
            catch (e: IOException) {
                println("IO error")
            } catch (e: HttpException) {
                println("HTTP error")
            }
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as CurrenciesApplication)
                AppViewModel(fixerRepository = application.container.fixerRepository)
            }
        }
    }
}