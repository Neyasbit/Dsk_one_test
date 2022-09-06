package com.example.feature_complexs.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.colorResource
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
    Text(
        text = room.type.uiName,
        fontSize = 12.sp,
        modifier = Modifier
            .border(
                width = 2.dp,
                if (room.isPressed) colorResource(id = R.color.light_blue) else Gray,
                shape = RoundedCornerShape(4.dp)
            )
            .clickable {
                viewModel.processUiEvent(
                    ComplexUiEvent.OnRoomClicked(
                        room.copy(
                            isPressed = !room.isPressed
                        )
                    )
                )
            }
            .padding(start = 11.dp, end = 11.dp, top = 8.dp, bottom = 8.dp)
    )
    Spacer(modifier = Modifier.width(10.dp))
}

@Composable
internal fun FilterTittle(
    tittle: String,
    normalTextFontSize: TextUnit = MaterialTheme.typography.subtitle1.fontSize
) {
    Text(text = tittle, fontSize = normalTextFontSize)
}