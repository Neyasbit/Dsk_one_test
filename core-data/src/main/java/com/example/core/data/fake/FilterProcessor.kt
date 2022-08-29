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
import javax.security.auth.login.LoginException

interface FilterProcessor {

    fun filtering(filters: Filters): Single<List<DskComplex>>

    class Base : FilterProcessor {

        override fun filtering(filters: Filters): Single<List<DskComplex>> =
            listOfComplex
                .toObservable()
                .map { it.toDskComplex() }
                .doOnNext {
                    Log.e("TAG", "areaFiltering: ${it.title}")
                }
                .filter { roomFilter(filters.rooms, it) }
                //.filter { areaFilter(filters.areaRange, it) }
                //.filter { priceFilter(filters.priceRange, it) }
                .doOnNext {
                    Log.e("TAG123", "areaFiltering: ${it.title}")
                }
                .toList()

        private fun areaFilter(areaRange: AreaRange, complex: DskComplex) =
            areaRange.range.start.toInt() in complex.areaRange
                    || areaRange.range.endInclusive.toInt() in complex.areaRange

        private fun priceFilter(priceRange: PriceRange, complex: DskComplex): Boolean {
            val a2 = priceRange.range.start.toInt()..priceRange.range.endInclusive.toInt()

            val a = priceRange.range.start.toInt() in complex.priceRange
                    || priceRange.range.endInclusive.toInt() in complex.priceRange
            Log.e("TAG", "priceFilter: a = $a $priceRange complex ${complex.priceRange} $a2")
            return a
        }

        private fun roomFilter(rooms: List<Int>, complex: DskComplex): Boolean {
            Log.e("1TAG", "roomFilter: $rooms ${complex.rooms}", )
            Log.e("1TAG", "roomFilter: ${complex.title} : ${complex.rooms.containsAll(rooms)}")
            return complex.rooms.containsAll(rooms)
        }

    }
}