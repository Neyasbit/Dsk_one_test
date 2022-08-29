package com.example.core_model

import org.threeten.bp.LocalDate

data class DskComplex(
    val title: String,
    val image: String,
    val areaRange: IntRange,
    val priceRange: IntRange,
    val labels: List<ComplexLabel>,
    val transport: ComplexTransport,
    val rooms: List<Int>,
    val builds: List<LocalDate>
)

data class ComplexLabel(
    val color: String,
    val title: String
)

data class ComplexTransport(
    val color: String,
    val from: String,
    val route: ComplexRoute

) {
    data class ComplexRoute(
        val time: Int,
        val type: String
    )
}


