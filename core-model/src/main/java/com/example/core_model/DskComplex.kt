package com.example.core_model

data class DskComplex(
    val title: String,
    val image: String,
    val areaRange: IntRange,
    val priceRange: IntRange,
    val labels: List<ComplexLabel>,
    val transport: ComplexTransport,
    val rooms: List<Int>
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


