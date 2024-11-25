package com.example.dva232.view.navigation

import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit

sealed class BottomNavItem(val title: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val route: String) {
    object Convert : BottomNavItem("Convert", androidx.compose.material.icons.Icons.Default.Edit, "convert")
    object Currencies : BottomNavItem("Currencies", androidx.compose.material.icons.Icons.Default.DateRange, "currencies")
}