package com.example.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Label(
    val color: String,
    val link: String,
    @SerialName("link-dsk")
    val linkDsk: String,
    val src: String,
    val title: String
)



