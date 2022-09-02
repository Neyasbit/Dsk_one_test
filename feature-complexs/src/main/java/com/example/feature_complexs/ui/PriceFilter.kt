package com.example.feature_complexs.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.RangeSlider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.feature_complexs.ComplexUiEvent
import com.example.feature_complexs.ComplexViewModel
import com.example.feature_complexs.R

@Composable
internal fun PriceFilter(viewModel: ComplexViewModel) {
    FilterTittle(stringResource(id = R.string.price_tittle))
    RangeContainer2(viewModel)
}

//todo one make one container with area
@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun RangeContainer2(
    viewModel: ComplexViewModel
) {
    val viewState =
        viewModel.viewState.subscribeAsState(initial = viewModel.viewState.blockingFirst())

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = viewState.value.filters.priceRange.firstVisibleItem)
        Text(text = viewState.value.filters.priceRange.secondVisibleItem)
    }

    RangeSlider(
        values = viewState.value.filters.priceRange.range,
        onValueChange = {
            viewModel.processUiEvent(ComplexUiEvent.OnPriceRangeChanged(it))
        },
        colors = SliderDefaults.colors(),
        valueRange = viewState.value.filters.priceRange.initialRange
    )
}