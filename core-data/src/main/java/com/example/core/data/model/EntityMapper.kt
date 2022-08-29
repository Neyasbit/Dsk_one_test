package com.example.core.data.model

import com.example.core_model.ComplexLabel
import com.example.core_model.ComplexTransport
import com.example.core_model.DskComplex
import org.threeten.bp.LocalDate

interface EntityMapper<Entity, DomainEntity> {
    fun Entity.mapFromEntity(): DomainEntity
}

fun DskResponceItem.toDskComplex(): DskComplex = DskComplex(
    title = title,
    image = img,
    areaRange = area_from..area_to,
    priceRange = price_from..price_to,
    labels = labels.map { it.toComplexLabel() },
    transport = transport.toComplexTransport(),
    rooms = rooms,
    builds = build.filterNotNull().map { LocalDate.parse(it) }
)

fun Label.toComplexLabel(): ComplexLabel = ComplexLabel(
    color = color,
    title = title
)

fun Transport.toComplexTransport(): ComplexTransport = ComplexTransport(
    color = color,
    from = from,
    route = route.first().toComplexRoute()
)

fun Route.toComplexRoute(): ComplexTransport.ComplexRoute =
    ComplexTransport.ComplexRoute(
        time,
        type
    )