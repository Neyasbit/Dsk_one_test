package com.example.feature_complexs.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import com.example.feature_complexs.ComplexUiEvent
import com.example.feature_complexs.ComplexViewModel
import com.example.feature_complexs.R
import com.example.feature_complexs.ViewState

@Composable
internal fun AreaFilter(viewModel: ComplexViewModel) {
    AreaTittle(normalText = stringResource(id = R.string.area_tittle), topText = "2")
    RangeContainer(viewModel)
}

@Composable
internal fun AreaTittle(
    normalText: String,
    normalTextFontSize: TextUnit = MaterialTheme.typography.subtitle1.fontSize,
    topText: String,
    topTextFontSize: TextUnit = MaterialTheme.typography.overline.fontSize
) {
    Text(text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontSize = normalTextFontSize)) {
            append(normalText.uppercase())
        }
        withStyle(
            style = SpanStyle(
                fontSize = topTextFontSize,
                fontWeight = FontWeight.Normal,
                baselineShift = BaselineShift.Superscript
            )
        ) {
            append(topText)
        }
    })
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun RangeContainer(
    viewModel: ComplexViewModel,
    valueRange: ClosedFloatingPointRange<Float> = 22f..109f
) {

    val viewState = viewModel.viewState.subscribeAsState(initial = ViewState())
    var sliderPosition by remember { mutableStateOf(22f..109f) }


    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = viewState.value.areaRange.firstVisibleItem)
        Text(text = viewState.value.areaRange.secondVisibleItem)
    }

    RangeSlider(
        values = sliderPosition,
        onValueChange = {
            viewModel.processUiEvent(ComplexUiEvent.OnAreaRangeChanged(it))
            sliderPosition = it

        },
        colors = SliderDefaults.colors(),
        valueRange = valueRange
    )
}