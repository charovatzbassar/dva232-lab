package com.example.dva232.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.dva232.CurrenciesApplication
import com.example.dva232.view.util.Currency
import com.example.dva232.view.util.FixerResponse
import com.example.dva232.viewModel.repos.FixerRepository
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import kotlin.reflect.KProperty

class AppViewModel(private val fixerRepository: FixerRepository) : ViewModel() {
    val response = mutableStateOf(FixerResponse(null, null, null, null, null))
    var currencies = mutableListOf<Currency>()

    init {
        getCurrencies()
    }


    fun getCurrencies() {
        viewModelScope.launch {
            try {
                response.value = fixerRepository.getCurrencies()
                response.value.rates?.forEach { (code, rate) ->
                    run {
                        currencies.add(Currency(code, rate))
                    }
                }
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

