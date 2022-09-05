package com.example.feature_complexs.ui

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.feature_complexs.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@JvmInline
value class Promo(@DrawableRes val image: Int)

private val listItems: List<Promo> =
    listOf(
        Promo(R.drawable.ic_promo),
        Promo(R.drawable.ic_promo_two),
        Promo(R.drawable.ic_promo_three)
    )

@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun PromoItem() {
    Spacer(Modifier.height(20.dp))

    val pagerState = rememberPagerState()

    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(2000)
            tween<Float>(600)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount)
            )
        }
    }

    Column {
        HorizontalPager(
            count = listItems.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 32.dp),
            modifier = Modifier
                .fillMaxWidth(),
        ) { page ->

            Image(
                painter = painterResource(id = listItems[page].image),
                contentDescription = null,
                contentScale = Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp)
                    .shadow(6.dp)
            )
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
        )
    }

}