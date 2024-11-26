package com.example.dva232.view.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.toSize

@Composable
fun DropdownInput(
    text: String,
    options: List<String>,
    selectedOption: MutableState<String>,
    modifier: Modifier = Modifier
) {
    var isDropdownExpanded by remember { mutableStateOf(false) }
    val textFieldSize = remember { mutableStateOf(androidx.compose.ui.geometry.Size.Zero) }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = selectedOption.value,
            onValueChange = { },
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    textFieldSize.value = coordinates.size.toSize()
                }
                .clickable { isDropdownExpanded = true }
                .fillMaxWidth(),
            label = { Text(text) },
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = if (isDropdownExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "Dropdown icon",
                    Modifier.clickable { isDropdownExpanded = !isDropdownExpanded }
                )
            }
        )

        DropdownMenu(
            expanded = isDropdownExpanded,
            onDismissRequest = { isDropdownExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.value.width.toDp() })
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        selectedOption.value = option
                        isDropdownExpanded = false
                    }
                )
            }
        }
    }
}