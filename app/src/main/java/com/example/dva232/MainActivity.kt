package com.example.dva232

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.dva232.ui.navigation.NavigationComposable
import com.example.dva232.ui.theme.DVA232Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            DVA232Theme {
                NavigationComposable()
            }
        }
    }
}
