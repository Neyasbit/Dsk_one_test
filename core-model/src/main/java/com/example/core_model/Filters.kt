package com.example.core_model

import org.threeten.bp.LocalDate
import java.util.*

data class AreaRange(val range: ClosedFloatingPointRange<Float> = 0f..1f) {
    val firstVisibleItem = String.format(Locale.ROOT, "%.0f", range.start)
    val secondVisibleItem = String.format(Locale.ROOT, "%.0f", range.endInclusive)
}

data class PriceRange(val range: ClosedFloatingPointRange<Float> = 0f..1f) {

    companion object {
        private fun priceFormatter(number: Float) =
            String.format(Locale.ROOT, "%.1fM", number / 1000000)
    }
    val firstVisibleItem = priceFormatter(range.start)
    val secondVisibleItem = priceFormatter(range.endInclusive)
}

data class BuildQuarter(val quarters: List<Pair<Int, Int>> = emptyList())

data class Filters(
    val areaRange: AreaRange = AreaRange(),
    val priceRange: PriceRange = PriceRange(),
    val buildQuarter: BuildQuarter = BuildQuarter(),
    val rooms: List<Int> = emptyList(),
    val builds: List<LocalDate> = emptyList()
)

data class Room(
    val type: RoomsType,
    val isPressed: Boolean = true
)

enum class RoomsType(val uiName: String) {
    ZERO("C"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5")
}

interface QuarterGenerator {

    private enum class Quarters(val value: Int) {
        ONE(1),
        TWO(2),
        THREE(3)
    }

    fun generationWithStart(startQuarter: Int, year: Int): List<String>

    fun simpleGeneration(year: Int): List<String>

    class Base : QuarterGenerator {
        override fun generationWithStart(startQuarter: Int, year: Int) = when (startQuarter) {
            Quarters.ONE.value -> (startQuarter..Quarters.THREE.value).toVisibleItem(year)
            Quarters.TWO.value -> (startQuarter..Quarters.THREE.value).toVisibleItem(year)
            else -> listOf("$startQuarter кв $year")
        }

        override fun simpleGeneration(year: Int): List<String> =
            (Quarters.ONE.value..Quarters.THREE.value).toVisibleItem(year)

        private fun IntRange.toVisibleItem(year: Int) = map { "$it кв $year" }
    }
}