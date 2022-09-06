package com.example.feature_complexs.ui

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.feature_complexs.ComplexViewModel
import com.example.feature_complexs.ComplexViewModelFactory
import com.example.feature_complexs.R
import com.example.feature_complexs.ViewState

@Composable
fun MainScreen() {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Content()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Content(
    viewModel: ComplexViewModel = viewModel(
        factory = ComplexViewModelFactory()
    )
) {
    val vs = viewModel.viewState.subscribeAsState(initial = ViewState())

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetElevation = 8.dp,
        sheetShape = RoundedCornerShape(
            bottomStart = 0.dp,
            bottomEnd = 0.dp,
            topStart = 12.dp,
            topEnd = 12.dp
        ),
        sheetGesturesEnabled = true,
        sheetContent = {
            BottomSheetContent(
                viewModel = viewModel,
                bottomSheetScaffoldState.bottomSheetState
            )
        },
        drawerGesturesEnabled = true,
        drawerScrimColor = Color(0xff000000),
        sheetPeekHeight = 0.dp,
    ) {
        Scaffold(
            topBar = {
                TopAppbar(bottomSheetState = bottomSheetScaffoldState.bottomSheetState)
            },
            bottomBar = {
                BottomAppBarComponent()
            },
            floatingActionButton = {

                val infiniteTransition = rememberInfiniteTransition()
                val color by infiniteTransition.animateColor(
                    initialValue = Blue,
                    targetValue = Color.Green,
                    animationSpec = infiniteRepeatable(
                        animation = tween(1000, easing = LinearEasing),
                        repeatMode = RepeatMode.Reverse
                    )
                )

                FloatingActionButton(backgroundColor = color, onClick = {}, contentColor = White) {
                    Icon(painterResource(id = R.drawable.ic_call), contentDescription = null)
                }
            }
        ) {
            MainContent(viewModel = viewModel, paddingValues = it)
        }
    }
}