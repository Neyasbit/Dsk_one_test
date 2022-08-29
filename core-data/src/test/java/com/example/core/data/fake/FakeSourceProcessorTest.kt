package com.example.core.data.fake

import io.reactivex.rxjava3.core.Observable
import kotlinx.serialization.json.Json
import org.junit.Test
import java.util.concurrent.TimeUnit


internal class FakeSourceProcessorTest {

    private val json = Json { ignoreUnknownKeys = true }
    //private val listOfComplex = json.decodeFromString<List<DskResponceItem>>(FakeDataSource.data)

    @Test
    fun testDecodeSubscribers() {

      /*  val subscriber = TestSubscriber<List<DskResponceItem>>()
        subscriber.onNext(listOfComplex)
        println(listOfComplex)
        assert(subscriber.values().isNotEmpty())*/
    }

    val a = Observable
        .combineLatest(Observable.just(7), Observable.just("S")) { one, two ->
            println("$one + $two")
        }
        .delay(2, TimeUnit.SECONDS)

    @Test
    fun `checking combine returns`() {
        //val testSubscriber = TestSubscriber<ComplexDataResult>()

       /* Observable.create<Result<ComplexData>> { emitter ->
            emitter.onNext(Result.Success(throw Exception("test123")))
        }.doOnNext { throw Exception("test") }.doOnError {
            println(it.message)
            println(12)
        }.subscribe(
            {},

            ) {
            println("xui $it")
        }*/

        Observable.create<String> { emitter ->
            println("xui")
            emitter.onNext("MindOrks")
            emitter.onComplete()
        }
            .subscribe {
                println("DelayExample $it")
            }

    }

    @Test
    fun testGsonConverter() {
        /*val gson = Gson()
        val type = object : TypeToken<Collection<DskResponceItem>>() {}.type
        val listOfComplex =
            gson.fromJson<List<DskResponceItem>>(FakeDataSource.data, type)
        println(listOfComplex)
        assert(listOfComplex.isNotEmpty())*/
    }

    @Test
    fun testKlaxonConverter() {
       /* val listOfComplex = Klaxon().parseArray<DskResponceItem>(FakeDataSource.data)
        println(listOfComplex)
        assert(listOfComplex!!.isNotEmpty())*/
    }
}