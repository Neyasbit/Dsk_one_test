package com.example.feature_complexs.ui

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetState
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import com.example.feature_complexs.ComplexUiEvent
import com.example.feature_complexs.ComplexViewModel
import com.example.feature_complexs.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun BottomSheetContent(viewModel: ComplexViewModel, bottomSheetState: BottomSheetState) {

    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .padding(all = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(6.dp))
        BottomSheetTittle(bottomSheetState, viewModel)
        Spacer(modifier = Modifier.height(10.dp))
        RoomFilter(viewModel)
        Spacer(modifier = Modifier.height(10.dp))
        AreaFilter(viewModel)
        Spacer(modifier = Modifier.height(10.dp))
        PriceFilter(viewModel)
        Spacer(modifier = Modifier.height(10.dp))
        BuildQuarterContainer(viewModel)
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor =
                colorResource(id = R.color.light_blue)
            ),
            onClick = { scope.launch { bottomSheetState.collapse() } }) {
            Text(text = stringResource(id = R.string.show), fontWeight = Bold, color = White)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun BottomSheetTittle(bottomSheetState: BottomSheetState, viewModel: ComplexViewModel) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.Reset),
            color = Gray,
            fontSize = MaterialTheme.typography.subtitle2.fontSize,
            modifier = Modifier.clickable { Toast.makeText(context, "TODO", Toast.LENGTH_SHORT).show() }
        )
        Text(
            text = stringResource(id = R.string.filters),
            fontWeight = Bold,
            fontSize = MaterialTheme.typography.h6.fontSize
        )
        IconButton(onClick = { scope.launch { bottomSheetState.collapse() } }) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(R.string.close)
            )
        }
    }
}