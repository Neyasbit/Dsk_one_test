package com.example.feature_complexs.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.core_model.DskComplex
import com.example.feature_complexs.ComplexViewModel
import com.example.feature_complexs.R

@Composable
fun MainContent(viewModel: ComplexViewModel, paddingValues: PaddingValues) {

    val vs = viewModel.viewState.subscribeAsState(initial = viewModel.viewState.blockingFirst())

    Column(
        modifier = Modifier
            .padding(all = 16.dp)
    ) {
        Spacer(Modifier.height(20.dp))

        AnimatedVisibility(visible = !vs.value.isVisibleFilters) {
            PromoItem()
        }

        LazyColumn(
            modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())
        ) {
            items(items = vs.value.complexes, key = { it.title.hashCode() }) { complex ->
                ComplexItem(complex)
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyItemScope.ComplexItem(
    complex: DskComplex
) {
    Spacer(modifier = Modifier.height(6.dp))

    Box(modifier = Modifier.animateItemPlacement(animationSpec = tween(500))) {
        AsyncImage(
            model = complex.image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(323.dp)
                .clip(RoundedCornerShape(14.dp))
        )
        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 10.dp)
        ) {
            complex.labels.forEach {
                Label(content = it.title, it.color)
            }
        }
    }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = complex.title, fontWeight = FontWeight.Bold)
        Text(
            text = stringResource(
                id = R.string.price_from,
                complex.priceRange.first / 1000000.0f
            ),
            color = Color.Red, fontWeight = FontWeight.Bold
        )
    }
    Row {
        Text(text = complex.transport.from, color = Color.Gray)
        Text(
            text = stringResource(id = R.string.time_tittle, complex.transport.route.time),
            color = Color.Gray,
            modifier = Modifier.padding(start = 5.dp)
        )
    }

    Spacer(modifier = Modifier.height(30.dp))
}

@Composable
private fun Label(content: String, color: String, modifier: Modifier = Modifier) {
    Text(
        text = content, modifier
            .padding(top = 10.dp)
            .shadow(4.dp, shape = RoundedCornerShape(6))
            .background(Color(android.graphics.Color.parseColor(color)))
            .padding(all = 2.dp),
        fontSize = MaterialTheme.typography.subtitle2.fontSize
    )
}