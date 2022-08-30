package com.example.core_model

import org.threeten.bp.LocalDate
import java.util.*

data class AreaRange(
    val initialRange: ClosedFloatingPointRange<Float> = 0f..1f,
    val range: ClosedFloatingPointRange<Float> = 0f..1f
) {
    val firstVisibleItem = String.format(Locale.ROOT, "%.0f", range.start)
    val secondVisibleItem = String.format(Locale.ROOT, "%.0f", range.endInclusive)
}

data class PriceRange(
    val initialRange: ClosedFloatingPointRange<Float> = 0f..1f,
    val range: ClosedFloatingPointRange<Float> = 0f..1f
) {

    companion object {
        private fun priceFormatter(number: Float) =
            String.format(Locale.ROOT, "%.1fM", number / 1000000)
    }

    val firstVisibleItem = priceFormatter(range.start)
    val secondVisibleItem = priceFormatter(range.endInclusive)
}

data class BuildQuarter(val quarters: List<Pair<String, LocalDate>> = emptyList())

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

    fun generationWithStart(startQuarter: Int, year: Int): List<Pair<String, LocalDate>>

    fun simpleGeneration(year: Int): List<Pair<String, LocalDate>>

    class Base : QuarterGenerator {
        override fun generationWithStart(startQuarter: Int, year: Int) = when (startQuarter) {
            Quarters.ONE.value -> {
                (startQuarter..Quarters.THREE.value).map {
                    Pair("$it кв $year", LocalDate.of(year, 3, 31))
                }
            }
            Quarters.TWO.value -> {
                (startQuarter..Quarters.THREE.value).map {
                    Pair("$it кв $year", LocalDate.of(year, 6, 30))
                }
            }
            else -> listOf(Pair("$startQuarter кв $year", LocalDate.of(year, 9, 30)))
        }

        override fun simpleGeneration(year: Int) = generationWithStart(1, year)
    }
}