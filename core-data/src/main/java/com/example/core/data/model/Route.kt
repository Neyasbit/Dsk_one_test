package com.example.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Route(
    val time: Int,
    val type: String
)