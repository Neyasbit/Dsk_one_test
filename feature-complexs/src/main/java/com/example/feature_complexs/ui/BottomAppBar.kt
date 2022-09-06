package com.example.feature_complexs.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feature_complexs.R


enum class BottomCell(val tittle: String, @DrawableRes val icon: Int, val tint: Color = Gray) {
    HOME("Объекты", R.drawable.ic_home, Red),
    MEGAPHONE("Акции", R.drawable.ic_megaphone),
    WALLET("Как купить", R.drawable.ic_wallet),
    FAVORITES("Избранное", R.drawable.ic_star),
    PERSON("Профиль", R.drawable.ic_person)
}

@Composable
internal fun BottomAppBarComponent() {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface,
    ) {
        BottomCell.values().forEach {
            BottomCell(tittle = it.tittle, painter = painterResource(id = it.icon), it.tint)
        }
    }
}

@Composable
internal fun RowScope.BottomCell(tittle: String, painter: Painter, tint: Color) {
    BottomNavigationItem(selected = true, onClick = { }, icon = {
        Icon(painter, contentDescription = null, tint = tint)
    }, modifier = Modifier.padding(all = 1.dp), label = {
        Text(text = tittle, fontSize = 8.sp)
    })
}