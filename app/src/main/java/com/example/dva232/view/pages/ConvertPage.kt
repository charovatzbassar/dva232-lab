package com.example.dva232.view.pages

import android.app.Activity
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.dva232.view.composables.DropdownInput
import com.example.dva232.view.composables.ModalDialog
import com.example.dva232.view.composables.Result
import com.example.dva232.view.util.Currency
import com.example.dva232.view.util.Functions

@Composable
fun ConvertPage(currencies: List<Currency>, baseCurrencyState: MutableState<String>) {
    var result by remember { mutableDoubleStateOf(0.0) }
    var isResultInvalid by remember { mutableStateOf(false) }

    var amount by remember { mutableStateOf("") }
    val selectedFromCurrency = remember { mutableStateOf("EUR") }
    val selectedToCurrency = remember { mutableStateOf("SEK") }
    val baseCurrency = remember { mutableStateOf(baseCurrencyState.value) }

    val options = currencies.map { c -> c.code }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Result(resultValue = result)

        when (LocalConfiguration.current.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    DropdownInput(
                        text = "From",
                        options = options,
                        selectedOption = selectedFromCurrency,
                        modifier = Modifier.weight(1f)
                    )

                    DropdownInput(
                        text = "To",
                        options = options,
                        selectedOption = selectedToCurrency,
                        modifier = Modifier.weight(1f)
                    )
                }

                OutlinedTextField(
                    value = amount,
                    onValueChange = { amount = it },
                    label = { Text("Amount") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )

            }

            else -> {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    DropdownInput(
                        text = "From",
                        options = options,
                        selectedOption = selectedFromCurrency,
                        modifier = Modifier.weight(0.33f)
                    )

                    DropdownInput(
                        text = "To",
                        options = options,
                        selectedOption = selectedToCurrency,
                        modifier = Modifier.weight(0.33f)
                    )

                    OutlinedTextField(
                        value = amount,
                        onValueChange = { amount = it },
                        label = { Text("Amount") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.33f)
                    )
                }
            }
        }

        Button(
            onClick = {
                try {
                    if (amount.toDouble() <= 0f) {
                        isResultInvalid = true
                        return@Button
                    }
                        val fromCurrency =
                            currencies.find { c -> c.code == selectedFromCurrency.value }
                        val toCurrency = currencies.find { c -> c.code == selectedToCurrency.value }

                        result = (toCurrency!!.rate * amount.toDouble()) / fromCurrency!!.rate
                } catch (e: NumberFormatException) {
                    isResultInvalid = true
                    return@Button
                }

            },
            modifier = Modifier.padding(top = 16.dp).fillMaxWidth()
        ) {
            Text("Submit")
        }

        when (LocalConfiguration.current.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                Row {
                    DropdownInput(
                        text = "Base Currency",
                        options = options,
                        selectedOption = baseCurrency,
                        modifier = Modifier.weight(1f).padding(top = 16.dp)
                    )

                }
                Button(
                    onClick = {
                        Functions.recalculateRates(currencies.toMutableList(), baseCurrency)
                        baseCurrencyState.value = baseCurrency.value
                    },
                    modifier = Modifier.padding(16.dp).fillMaxWidth()
                ) {
                    Text("Change Currency")
                }
            }

            else -> {
                Row(
                    modifier = Modifier.height(90.dp)
                ) {
                    DropdownInput(
                        text = "Base Currency",
                        options = options,
                        selectedOption = baseCurrency,
                        modifier = Modifier.weight(1f).padding(top = 16.dp)
                    )
                    Button(
                        onClick = {
                            if (baseCurrency.value == "Unknown") {
                                isResultInvalid = true
                                return@Button
                            }
                            Functions.recalculateRates(currencies.toMutableList(), baseCurrency)
                            baseCurrencyState.value = baseCurrency.value
                        },
                        modifier = Modifier.padding(16.dp).fillMaxHeight()
                    ) {
                        Text("Change Currency")
                    }
                }

            }

        }




        ModalDialog(text = "This action is invalid", isDialogOpen = isResultInvalid, onDismissRequest = {
            isResultInvalid = false
        })

    }
}