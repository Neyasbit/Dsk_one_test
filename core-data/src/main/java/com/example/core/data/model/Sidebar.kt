package com.example.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sidebar(
    @SerialName("apart-hotel")
    val apartHotel: Boolean,
    val apartments: Boolean,
    val contacts: Boolean,
    val decoration: Boolean,
    val document: Boolean,
    val evropeiskaya: Boolean,
    val flats: Boolean,
    val furniture: Boolean,
    val installment_plan: Boolean,
    val map: Boolean,
    val maternal_capital: Boolean,
    val military_mortgage: Boolean,
    val mortgage: Boolean,
    val netting: Boolean,
    val news: Boolean,
    val pantry: Boolean,
    val parking: Boolean,
    val progress: Boolean,
    val promo: Boolean,
    val smart: Boolean,
    val subsidies: Boolean
)