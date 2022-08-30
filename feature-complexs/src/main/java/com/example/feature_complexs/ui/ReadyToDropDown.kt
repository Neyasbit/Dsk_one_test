package com.example.feature_complexs.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.example.feature_complexs.ComplexUiEvent
import com.example.feature_complexs.ComplexViewModel
import com.example.feature_complexs.R

@Composable
internal fun ReadyToBuildDropDown(
    viewModel: ComplexViewModel
) {
    val viewState =
        viewModel.viewState.subscribeAsState(initial = viewModel.viewState.blockingFirst())
    var isSimpleDropDownExpanded by remember { mutableStateOf(false) }

    val builds = viewState.value.quarters.quarters
    //todo save in VM
    var current by remember { mutableStateOf("") }
    Text(text = stringResource(R.string.ready_tittle).uppercase())
    Box {
        OutlinedTextField(
            value = current,
            onValueChange = { },
            placeholder = { Text(text = builds.first().first) },
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
                    current = it.first
                    isSimpleDropDownExpanded = false
                    viewModel.processUiEvent(ComplexUiEvent.OnQuartersChanged(it.second))
                }, modifier = Modifier.fillMaxWidth()) {
                    Text(it.first)
                }
            }
        }
    }
}

