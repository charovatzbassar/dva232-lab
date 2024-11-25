package com.example.dva232.view.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dva232.view.composables.DropdownInput
import com.example.dva232.view.composables.Result
import com.example.dva232.view.util.Currency

@Composable
fun ConvertPage(currencies: List<Currency>, navController: NavController) {
    var result by remember { mutableDoubleStateOf(0.0) }

    var amount by remember { mutableStateOf("") }
    val selectedFromCurrency = remember { mutableStateOf("EUR") }
    val selectedToCurrency = remember { mutableStateOf("SEK") }

    val options = currencies.map { c -> c.code }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

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

        Button(
            onClick = {
                val fromCurrency = currencies.find { c -> c.code == selectedFromCurrency.value }
                val toCurrency = currencies.find { c -> c.code == selectedToCurrency.value }

                result = (toCurrency!!.rate * amount.toDouble()) / fromCurrency!!.rate
            },
            modifier = Modifier.padding(top = 16.dp).fillMaxWidth()
        ) {
            Text("Submit")
        }


        Result(resultValue = result)
    }
}