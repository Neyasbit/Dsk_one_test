package com.example.feature_complexs.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core_model.Room
import com.example.feature_complexs.ComplexUiEvent
import com.example.feature_complexs.ComplexViewModel
import com.example.feature_complexs.R
import com.example.feature_complexs.ViewState

@Composable
internal fun RoomFilter(viewModel: ComplexViewModel) {
    val viewState = viewModel.viewState.subscribeAsState(initial = ViewState()).value
    Column {
        FilterTittle(stringResource(id = R.string.rooms_amount))
        Spacer(modifier = Modifier.height(6.dp))
        Row {
            viewState.rooms.forEach {
                RoomBox(it, viewModel)
            }
        }
    }
}

@Composable
internal fun RoomBox(room: Room, viewModel: ComplexViewModel) {
    Column(modifier = Modifier.width(IntrinsicSize.Max)) {
        Text(
            text = room.type.uiName,
            fontSize = 12.sp,
            modifier = Modifier
                .border(
                    width = 1.dp,
                    Gray,
                    shape = RectangleShape
                )
                .clickable {
                    viewModel.processUiEvent(ComplexUiEvent.OnRoomClicked(room.copy(isPressed = !room.isPressed)))
                }
                .padding(all = 12.dp)
        )
        AnimatedVisibility(visible = room.isPressed) {
            Divider(color = Blue)
        }
    }
}

@Composable
internal fun FilterTittle(
    tittle: String,
    normalTextFontSize: TextUnit = MaterialTheme.typography.subtitle1.fontSize
) {
    Text(text = tittle.uppercase(), fontSize = normalTextFontSize)
}