package com.example.feature_complexs.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.feature_complexs.ComplexUiEvent
import com.example.feature_complexs.ComplexViewModel
import com.example.feature_complexs.R

@Composable
internal fun BuildQuarterContainer(viewModel: ComplexViewModel) {
    val quarters =
        viewModel.viewState
            .subscribeAsState(initial = viewModel.viewState.blockingFirst())
            .value.filters.buildQuarter.quarters
    Text(text = stringResource(id = R.string.ready_tittle))
    Spacer(modifier = Modifier.height(15.dp))
    Row {
        quarters.take(2).forEach {
            QuarterBox(tittle = it.first) {
                viewModel.processUiEvent(ComplexUiEvent.OnQuartersChanged(it))
            }
        }
    }

    quarters
        .groupBy { it.second.year }
        .filter { it.key > 2022 }
        .forEach {
            Text(
                text = it.key.toString(),
                modifier = Modifier
                    .padding(
                        bottom = 11.dp,
                        top = 15.dp
                    )
            )
            Row {
                it.value.forEach { pair ->
                    QuarterBox(tittle = pair.first) {
                        viewModel.processUiEvent(ComplexUiEvent.OnQuartersChanged(pair))
                    }
                }
            }
        }

}

@Composable
internal fun QuarterBox(tittle: String, onClickQuarter: () -> Unit) {
    Text(
        text = tittle,
        Modifier
            .border(
                2.dp,
                color = colorResource(id = R.color.light_blue),
                shape = RoundedCornerShape(4.dp)
            )
            .clickable { onClickQuarter.invoke() }
            .padding(start = 10.dp, end = 10.dp, top = 7.dp, bottom = 7.dp)

    )
    Spacer(modifier = Modifier.width(10.dp))
}