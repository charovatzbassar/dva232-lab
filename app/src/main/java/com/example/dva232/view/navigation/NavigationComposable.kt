package com.example.dva232.view.navigation

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.dva232.view.util.Functions
import com.example.dva232.viewModel.AppViewModel
import com.google.android.gms.location.LocationServices
import java.util.Locale

@Composable
fun NavigationComposable() {
    val navController = rememberNavController()
    val appViewModel: AppViewModel =
        viewModel(factory = AppViewModel.Factory)

    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val geocoder = remember { Geocoder(context, Locale.getDefault()) }
    val baseCurrencyCode = remember { mutableStateOf("Unknown") }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            if (granted) {
                Functions.getCurrencyForLocation(fusedLocationClient, geocoder, baseCurrencyCode, appViewModel.currencies)

            } else {
                baseCurrencyCode.value = "Unknown"
            }
        }
    )

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Functions.getCurrencyForLocation(fusedLocationClient, geocoder, baseCurrencyCode, appViewModel.currencies)
        } else {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }



    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavigationGraph(navController = navController, modifier = Modifier.padding(innerPadding), currencies = appViewModel.currencies, baseCurrencyState = baseCurrencyCode)
    }



}