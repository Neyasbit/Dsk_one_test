package com.example.core.data.model

import com.example.core_model.DskComplex

sealed class ComplexStatus {
    data class Success(val data: List<DskComplex>) : ComplexStatus()
    data class Error(val exception: Exception) : ComplexStatus()
    object InProgress : ComplexStatus()
}
