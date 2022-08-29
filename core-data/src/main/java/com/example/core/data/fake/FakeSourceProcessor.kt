package com.example.core.data.fake

import android.util.Log
import com.example.core.data.fake.FakeDataSource.listOfComplex
import com.example.core.data.model.ComplexStatus
import com.example.core.data.model.DskResponceItem
import com.example.core.data.model.toDskComplex
import com.example.core.data.repository.fake.ComplexDataResult
import com.example.core_model.AreaRange
import com.example.core_model.BuildQuarter
import com.example.core_model.PriceRange
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observable.just
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.toObservable
import io.reactivex.rxjava3.schedulers.Schedulers.newThread
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId
import org.threeten.bp.temporal.IsoFields
import java.util.concurrent.TimeUnit


interface FakeSourceProcessor {

    fun getFormattedComplexes(): Observable<ComplexDataResult>

    fun getAreaRange(): Single<AreaRange>

    fun getPriceRange(): Single<PriceRange>

    fun getBuildQuarters(): Single<BuildQuarter>


    class Base : FakeSourceProcessor {


        override fun getFormattedComplexes(): Observable<ComplexDataResult> =
            just(ComplexStatus.InProgress)
                .observeOn(newThread())
                //.delay(2, TimeUnit.SECONDS)
                .map {
                    ComplexStatus.Success(listOfComplex.map { complex -> complex.toDskComplex() })
                }

        override fun getAreaRange(): Single<AreaRange> =
            listOfComplex.toObservable().flatMap { complex ->
                just(complex.area_from, complex.area_to)
            }
                .toSortedList()
                .map { AreaRange(range = it.first().toFloat()..it.last().toFloat()) }

        override fun getPriceRange(): Single<PriceRange> =
            listOfComplex.toObservable().flatMap { complex ->
                just(complex.price_from.toFloat(), complex.price_to.toFloat())
            }
                .toSortedList()
                .map { PriceRange(range = it.first()..it.last()) }

        override fun getBuildQuarters(): Single<BuildQuarter> =
            listOfComplex.toObservable().flatMap { complex ->
                just(complex.build_from, complex.build_to)
            }
                .toSortedList()
                .flattenAsObservable { it }
                .map {
                    val date = LocalDate.parse(it)
                    val quarter = date.get(IsoFields.QUARTER_OF_YEAR)
                    val dateWithQuarter = Pair(quarter, date.year)
                    dateWithQuarter
                }
                .filter {
                    val now = LocalDate.now(ZoneId.systemDefault())
                    it.second >= now.year
                }
                .toList()
                .map { BuildQuarter(it) }
    }
}
