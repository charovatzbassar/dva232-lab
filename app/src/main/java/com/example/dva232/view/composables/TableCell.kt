package com.example.dva232.view.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TableCell(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(50.dp)
            .padding(4.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, fontSize = 16.sp)
    }
}