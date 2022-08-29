package com.example.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class File(
    val bucket: String,
    val created_at: String,
    val description: String?,
    val file_name: String,
    val id: Int,
    val is_img: Boolean,
    val original_name: String,
    val sort: Int,
    val title: String?,
    val type: String,
    val updated_at: String
)