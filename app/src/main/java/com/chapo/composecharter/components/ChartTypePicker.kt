package com.chapo.composecharter

import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*

@Composable
fun ChartTypePicker(onChartTypeChanged: (ChartType) -> Unit) {

    var text by remember {
        mutableStateOf("Choose Chart Type")
    }

    var expanded by remember {
        mutableStateOf(false)
    }
    val chartTypes = remember {
        ChartType.values()
    }

    Box {
        Button(onClick = { expanded = !expanded }) {
            Text(text)
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null,
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            chartTypes.forEach { type ->
                DropdownMenuItem(onClick = {
                    onChartTypeChanged(type)
                    text = type.toString()
                    expanded = false
                }) {
                    Text(text = type.toString())
                }
            }
        }
    }
}
