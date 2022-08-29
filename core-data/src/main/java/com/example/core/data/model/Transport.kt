package com.example.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Transport(
    val color: String,
    val from: String,
    val route: List<Route>
)