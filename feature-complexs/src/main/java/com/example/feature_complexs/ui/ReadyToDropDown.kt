package com.example.feature_complexs.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import com.example.feature_complexs.ComplexViewModel
import com.example.feature_complexs.ViewState


@Composable
internal fun ReadyToBuildDropDown(
    title: String,
    titleFontSize: TextUnit = MaterialTheme.typography.h6.fontSize,
    viewModel: ComplexViewModel
) {
    val viewState = viewModel.viewState.subscribeAsState(initial = ViewState())


    var isSimpleDropDownExpanded by remember { mutableStateOf(false) }
    val builds = listOf(
        "Все",
        "Сдан",
        "1 кв 2023",
        "2 кв 2023",
        "3 кв 2023",
        "1 кв 2024",
        "2 кв 2024",
        "3 кв 2024"
    )
    var current by remember { mutableStateOf("") }
    Text(text = "Готовность до".uppercase())
    Box {
        OutlinedTextField(
            value = current,
            onValueChange = { },
            placeholder = { Text(text = builds.first()) },
            enabled = false,
            modifier = Modifier
                .clickable {
                    isSimpleDropDownExpanded = true
                }
                .fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black)
        )

        DropdownMenu(
            expanded = isSimpleDropDownExpanded,
            onDismissRequest = { isSimpleDropDownExpanded = false },
            modifier = Modifier.fillMaxWidth(0.8f),
        ) {
            builds.forEach {
                DropdownMenuItem(onClick = {
                    current = it
                    isSimpleDropDownExpanded = false
                }, modifier = Modifier.fillMaxWidth()) {
                    Text(it)
                }
            }
        }
    }
}

