package com.example.feature_complexs.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.RangeSlider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.feature_complexs.ComplexUiEvent
import com.example.feature_complexs.ComplexViewModel
import com.example.feature_complexs.R
import com.example.feature_complexs.ViewState

@Composable
internal fun PriceFilter(viewModel: ComplexViewModel) {
    FilterTittle(stringResource(id = R.string.price_tittle))
    RangeContainer2(viewModel)
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun RangeContainer2(
    viewModel: ComplexViewModel
) {

    val viewState = viewModel.viewState.subscribeAsState(initial = ViewState())
    var sliderPosition by remember { mutableStateOf(5542000f..20204105f) }


    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Log.e("TAG", "RangeContainer2: ${viewState.value.priceRange.firstVisibleItem} ${viewState.value.priceRange}", )
        Text(text = viewState.value.priceRange.firstVisibleItem)
        Text(text = viewState.value.priceRange.secondVisibleItem)
    }

    RangeSlider(
        values = sliderPosition,
        onValueChange = {
            viewModel.processUiEvent(ComplexUiEvent.OnPriceRangeChanged(it))
            sliderPosition = it

        },
        colors = SliderDefaults.colors(),
        valueRange = 5542000f..20204105f
    )
}