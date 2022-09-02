package com.example.feature_complexs.ui

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.core_model.DskComplex
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

@Composable
private fun Content(
    viewModel: ComplexViewModel = viewModel(
        factory = ComplexViewModelFactory()
    )
) {
    val vs = viewModel.viewState.subscribeAsState(initial = ViewState())

    Column(
        modifier = Modifier
            .padding(all = 16.dp)
    ) {
        FiltersContainer(title = stringResource(R.string.filters)) {
            Spacer(modifier = Modifier.height(6.dp))
            RoomFilter(viewModel)
            Spacer(modifier = Modifier.height(6.dp))
            AreaFilter(viewModel)
            Spacer(modifier = Modifier.height(6.dp))
            PriceFilter(viewModel)
            Spacer(modifier = Modifier.height(6.dp))
            ReadyToBuildDropDown(viewModel)
            Spacer(modifier = Modifier.height(6.dp))
        }
        LazyColumn {
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
    Column(
        modifier =
        Modifier.animateItemPlacement(animationSpec = tween(500))
    ) {
        Box {
            AsyncImage(
                model = complex.image,
                contentDescription = null,
                contentScale = Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .border(color = Green, width = 2.dp, shape = RectangleShape),

                )
            Row(modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 10.dp)) {
                complex.labels.forEach {
                    Label(content = it.title, it.color)
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = complex.title)
            Text(
                text = stringResource(
                    id = R.string.price_from,
                    complex.priceRange.first / 1000000.0f
                ), color = Red
            )
        }
        Row {
            Text(text = complex.transport.from, color = Gray)
            Text(
                text = stringResource(id = R.string.time_tittle, complex.transport.route.time),
                color = Gray,
                modifier = Modifier.padding(start = 5.dp)
            )
        }
    }
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