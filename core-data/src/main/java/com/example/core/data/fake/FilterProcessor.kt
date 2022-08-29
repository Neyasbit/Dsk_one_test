package com.example.core.data.fake

import android.util.Log
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

        override fun filtering(filters: Filters): Single<List<DskComplex>> =
            listOfComplex
                .toObservable()
                .map { it.toDskComplex() }
                .doOnNext {
                    Log.e("1TAG", "filtering: start ${it.title}", )
                }
                .filter {
                    Log.e("1TAG", "${it.title} roomFilter: ${roomFilter(filters.rooms, it)}")
                    roomFilter(filters.rooms, it)
                }
                .filter {
                    Log.e("1TAG", "${it.title}  areaFilter: ${areaFilter(filters.areaRange, it)}")
                    areaFilter(filters.areaRange, it)
                }
                .filter {
                    Log.e("1TAG", "${it.title}  priceFilter: ${priceFilter(filters.priceRange, it)}")
                    priceFilter(filters.priceRange, it)
                }
                .filter {
                    Log.e("1TAG", "${it.title} buildsFilter: ${buildsFilter(filters.builds, it)}")
                    buildsFilter(filters.builds, it)
                }
                .doOnNext {
                    Log.e("1TAG", "filtering: end ${it.title}")
                }
                .toList()

        private fun areaFilter(areaRange: AreaRange, complex: DskComplex): Boolean {
            Log.e(
                "2TAG",
                "areaFilter: ${
                    areaRange.range.start.toInt() in complex.areaRange
                            && areaRange.range.endInclusive.toInt() in complex.areaRange
                            || (complex.areaRange.last <= areaRange.range.endInclusive
                            && complex.areaRange.first >= areaRange.range.start)
                }",
            )
            return areaRange.range.start.toInt() in complex.areaRange
                    && areaRange.range.endInclusive.toInt() in complex.areaRange
                    || (complex.areaRange.last <= areaRange.range.endInclusive
                    && complex.areaRange.first >= areaRange.range.start)
        }


        private fun priceFilter(priceRange: PriceRange, complex: DskComplex) =
            priceRange.range.start.toInt() in complex.priceRange
                    && priceRange.range.endInclusive.toInt() in complex.priceRange || ((complex.priceRange.last <= priceRange.range.endInclusive
                    && complex.priceRange.first >= priceRange.range.start))


        private fun roomFilter(rooms: List<Int>, complex: DskComplex) =
            complex.rooms.containsAll(rooms) || rooms.containsAll(complex.rooms)

        private fun buildsFilter(quarter: List<LocalDate>, complex: DskComplex): Boolean =
            complex.builds.any { it > quarter.first() }
    }
}