package com.example.dva232.view.pages

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dva232.view.composables.DropdownInput
import com.example.dva232.view.composables.TableCell
import com.example.dva232.view.util.currencyList
import com.example.dva232.viewModel.AppViewModel

@Composable
fun CurrenciesPage() {
    val appViewModel: AppViewModel =
        viewModel(factory = AppViewModel.Factory)

    val isPortrait = LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {

        if (isPortrait) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(8.dp)
            ) {
                TableCell("Currency Code", Modifier.weight(1f))
                TableCell("Exchange Rate", Modifier.weight(1f))
            }
        }

        LazyColumn {
            items(currencyList) { currency ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    TableCell(currency.code, Modifier.weight(1f))
                    TableCell(currency.rate.toString(), Modifier.weight(1f))
                }
            }
        }
    }



}


