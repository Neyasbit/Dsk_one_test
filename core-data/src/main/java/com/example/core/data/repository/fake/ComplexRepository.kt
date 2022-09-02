package com.example.core.data.repository.fake

import com.example.core.data.fake.FakeSourceProcessor
import com.example.core.data.fake.FilterProcessor
import com.example.core.data.model.ComplexStatus
import com.example.core.data.model.DskResponceItem
import com.example.core_model.DskComplex
import com.example.core_model.Filters
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

typealias ComplexDataResult = ComplexStatus
typealias ComplexData = List<DskResponceItem>
typealias BuildPairs = List<Pair<Int, Int>>

interface ComplexRepository {

    val filters: Observable<Filters>

    fun getComplex(): Observable<ComplexStatus>

    fun filtering(filters: Filters): Single<List<DskComplex>>

    class Base(
        private val fakeSourceProcessor: FakeSourceProcessor,
        private val filterProcessor: FilterProcessor
    ) : ComplexRepository {

        override val filters: Observable<Filters> = Observable.combineLatest(
            fakeSourceProcessor.getAreaRange().toObservable(),
            fakeSourceProcessor.getPriceRange().toObservable(),
            fakeSourceProcessor.getBuildQuarters().toObservable()
        ) { areaRange, priceRage, quarters -> Filters(areaRange, priceRage, quarters) }

        override fun getComplex(): Observable<ComplexStatus> =
            fakeSourceProcessor.getFormattedComplexes()

        override fun filtering(filters: Filters): Single<List<DskComplex>> =
            filterProcessor.filtering(filters)
    }
}