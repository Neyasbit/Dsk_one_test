package com.example.feature_complexs.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
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

    val builds = viewState.value.filters.buildQuarter.quarters

    Text(text = stringResource(R.string.ready_tittle).uppercase())
    Box {
        OutlinedTextField(
            value = viewState.value.filters.sortedDate.first,
            onValueChange = { },
            placeholder = { Text(text = builds.first().first) },
            enabled = false,
            modifier = Modifier
                .clickable {
                    isSimpleDropDownExpanded = true
                }
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(2.dp)
                ),
            textStyle = TextStyle(color = Color.Black)
        )

        DropdownMenu(
            expanded = isSimpleDropDownExpanded,
            onDismissRequest = { isSimpleDropDownExpanded = false },
            modifier = Modifier.fillMaxWidth(0.8f),
        ) {
            builds.forEach {
                DropdownMenuItem(onClick = {
                    isSimpleDropDownExpanded = false
                    viewModel.processUiEvent(ComplexUiEvent.OnQuartersChanged(it))
                }, modifier = Modifier.fillMaxWidth()) {
                    Text(it.first)
                }
            }
        }
    }
}

