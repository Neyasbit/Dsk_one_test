package com.example.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Flats(
    val all: Int,
    val booking: Int,
    val built: Int,
    val finish: Int,
    val furniture: Int
)