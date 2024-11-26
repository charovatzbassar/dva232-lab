package com.example.dva232.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dva232.view.pages.ConvertPage
import com.example.dva232.view.pages.CurrenciesPage
import com.example.dva232.view.util.currencyList

@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = BottomNavItem.Convert.route, modifier = modifier) {
        composable(BottomNavItem.Convert.route) { ConvertPage() }
        composable(BottomNavItem.Currencies.route) { CurrenciesPage() }
    }
}