package com.example.common_data

import com.example.core.data.fake.FakeDataSource
import com.example.core.data.model.DskResponceItem
import com.example.core.data.repository.fake.ComplexRepository
import io.reactivex.rxjava3.kotlin.toObservable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

internal class ComplexRepositoryTest {

    private val json = Json { ignoreUnknownKeys = true }
    //private val complexRepositoryTest = ComplexRepository.Base()
    private val listOfComplex = json.decodeFromString<List<DskResponceItem>>(FakeDataSource.data)


    @Test
    fun `filtering area form`() {
        val filterItems =
            listOfComplex.toObservable().filter { it.area_from <= 22 }.blockingFirst()
        assert(filterItems.title == "Южная Битца")
    }

    @Test
    fun `return list`() {
        val items: List<DskResponceItem> = listOfComplex.toObservable().toList().blockingGet()
        println(items)
        assert(items.isNotEmpty())
    }

}