package com.example.dva232.view.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dva232.view.composables.TableCell
import com.example.dva232.view.util.Currency

@Composable
fun CurrenciesPage(currencies: List<Currency>, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(vertical = 8.dp)
        ) {
            TableCell("Currency Code", Modifier.weight(1f))
            TableCell("Exchange Rate", Modifier.weight(1f))
        }

        LazyColumn {
            items(currencies) { currency ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    TableCell(currency.code, Modifier.weight(1f))
                    TableCell(currency.rate.toString(), Modifier.weight(1f))
                }
            }
        }
    }



}


