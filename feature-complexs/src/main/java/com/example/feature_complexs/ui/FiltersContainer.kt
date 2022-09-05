package com.example.feature_complexs.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.feature_complexs.ComplexUiEvent
import com.example.feature_complexs.ComplexViewModel


@Composable
internal fun FiltersContainer(
    viewModel: ComplexViewModel,
    title: String,
    titleFontSize: TextUnit = MaterialTheme.typography.h6.fontSize,
    content: @Composable () -> Unit
) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = LinearOutSlowInEasing
                    )
                )
                .border(
                    width = 2.dp,
                    color = Gray,
                    shape = RoundedCornerShape(2.dp)
                )
                .clickable {
                    expandedState = !expandedState
                    viewModel.processUiEvent(ComplexUiEvent.VisibilityFilters(expandedState))
                }
                .padding(start = 6.dp)
        ) {
            Text(
                modifier = Modifier
                    .weight(6f),
                text = title,
                fontSize = titleFontSize,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            IconButton(
                modifier = Modifier
                    .weight(1f)
                    .alpha(ContentAlpha.medium)
                    .rotate(rotationState),
                onClick = {
                    expandedState = !expandedState
                    viewModel.processUiEvent(ComplexUiEvent.VisibilityFilters(expandedState))
                }) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Drop-Down Arrow"
                )
            }
    }
    AnimatedVisibility(
        visible = expandedState,
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut() + slideOutVertically()
    ) {
        Column {
            content()
        }
    }
}

