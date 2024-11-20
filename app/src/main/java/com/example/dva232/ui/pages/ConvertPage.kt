package com.example.dva232.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dva232.ui.composables.DropdownInput
import com.example.dva232.ui.util.Currency

@Composable
fun ConvertPage(currencies: List<Currency>, navController: NavController) {
    var result by remember { mutableDoubleStateOf(0.0) }

    var amount by remember { mutableStateOf("") }
    val selectedFromCurrency = remember { mutableStateOf("EUR") }
    val selectedToCurrency = remember { mutableStateOf("SEK") }

    val options = currencies.map { c -> c.code }

    Column {
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        DropdownInput(
            text = "From",
            options = options,
            selectedOption = selectedFromCurrency
        )

        DropdownInput(
            text = "To",
            options = options,
            selectedOption = selectedToCurrency
        )


        Button(
            onClick = {
                val fromCurrency = currencies.find { c -> c.code == selectedFromCurrency.value }
                val toCurrency = currencies.find { c -> c.code == selectedToCurrency.value }

                result = (toCurrency!!.rate * amount.toDouble()) / fromCurrency!!.rate
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Submit")
        }


        Text(text = "$result")
    }
}