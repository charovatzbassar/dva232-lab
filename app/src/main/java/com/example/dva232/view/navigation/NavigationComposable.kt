package com.example.dva232.view.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationComposable() {
    val navController = rememberNavController()

    when (LocalConfiguration.current.orientation) {
        else -> {
            Scaffold(
                bottomBar = { BottomNavigationBar(navController) }
            ) { innerPadding ->
                NavigationGraph(navController = navController, modifier = Modifier.padding(innerPadding))
            }
        }
    }


}