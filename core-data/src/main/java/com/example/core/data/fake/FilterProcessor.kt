package com.example.core.data.fake

import com.example.core.data.fake.FakeDataSource.listOfComplex
import com.example.core.data.model.toDskComplex
import com.example.core_model.AreaRange
import com.example.core_model.DskComplex
import com.example.core_model.Filters
import com.example.core_model.PriceRange
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.toObservable
import org.threeten.bp.LocalDate

interface FilterProcessor {

    fun filtering(filters: Filters): Single<List<DskComplex>>

    class Base : FilterProcessor {

        private val dskComplexList = listOfComplex.toObservable().map { it.toDskComplex() }

        override fun filtering(filters: Filters): Single<List<DskComplex>> =
            dskComplexList
                .filter { roomFilter(filters.rooms, it) }
                .filter { areaFilter(filters.areaRange, it) }
                .filter { priceFilter(filters.priceRange, it) }
                .filter { buildsFilter(filters.sortedDate.second, it) }
                .toList()

        private fun areaFilter(areaRange: AreaRange, complex: DskComplex): Boolean {
            return areaRange.range.start.toInt() in complex.areaRange
                    || areaRange.range.endInclusive.toInt() in complex.areaRange
                    || (complex.areaRange.last <= areaRange.range.endInclusive
                    && complex.areaRange.first >= areaRange.range.start)
        }


        private fun priceFilter(priceRange: PriceRange, complex: DskComplex) =
            priceRange.range.start.toInt() in complex.priceRange
                    || priceRange.range.endInclusive.toInt() in complex.priceRange
                    || ((complex.priceRange.last <= priceRange.range.endInclusive
                    && complex.priceRange.first >= priceRange.range.start))


        private fun roomFilter(rooms: List<Int>, complex: DskComplex) =
            complex.rooms.containsAll(rooms) || rooms.containsAll(complex.rooms)

        private fun buildsFilter(quarter: LocalDate, complex: DskComplex): Boolean =
            complex.builds.any { it > quarter }
    }
}