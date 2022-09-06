package com.example.feature_complexs.ui

import androidx.compose.material.BottomSheetState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.feature_complexs.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun TopAppbar(
    bottomSheetState: BottomSheetState
) {
    val coroutineScope = rememberCoroutineScope()

    TopAppBar(
        title = { TopBarTittle() },
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface,
        actions = {
            IconButton(onClick = {
                coroutineScope.launch {
                    if (bottomSheetState.isCollapsed)
                        bottomSheetState.expand()
                    else bottomSheetState.collapse()
                }
            }) {
                Icon(
                    painterResource(id = R.drawable.ic_filter), contentDescription = stringResource(
                        R.string.ic_filter
                    )
                )
            }

            IconButton(onClick = { }) {
                Icon(
                    painterResource(id = R.drawable.ic_map),
                    contentDescription = stringResource(R.string.map)
                )
            }
        }
    )
}

@Composable
internal fun TopBarTittle() {
    Text(text = stringResource(R.string.top_bar_tittle))
    IconButton(onClick = { /*TODO*/ }) {
        Icon(Icons.Default.ArrowDropDown, contentDescription = "")
    }
}