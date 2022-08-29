package com.example.feature_complexs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.BaseViewModel
import com.example.core.DataEvent
import com.example.core.ErrorEvent
import com.example.core.Event
import com.example.core.UiEvent
import com.example.core.data.fake.FakeSourceProcessor
import com.example.core.data.fake.FilterProcessor
import com.example.core.data.model.ComplexStatus
import com.example.core.data.repository.fake.ComplexRepository
import com.example.core_model.AreaRange
import com.example.core_model.BuildQuarter
import com.example.core_model.DskComplex
import com.example.core_model.Filters
import com.example.core_model.PriceRange
import com.example.core_model.QuarterGenerator
import com.example.core_model.Room
import com.example.core_model.RoomsType

data class ViewState(
    val complexes: List<DskComplex> = emptyList(),
    val rooms: List<Room> = emptyList(),
    val areaRange: AreaRange = AreaRange(),
    val priceRange: PriceRange = PriceRange(),
    val quarters: BuildQuarter = BuildQuarter(),
    val builds: List<String> = emptyList()
)

sealed class ComplexUiEvent : UiEvent {
    data class OnRoomClicked(val room: Room) : ComplexUiEvent()
    data class OnAreaRangeChanged(val range: ClosedFloatingPointRange<Float>) : ComplexUiEvent()
    data class OnPriceRangeChanged(val range: ClosedFloatingPointRange<Float>) : ComplexUiEvent()
}

sealed class ComplexDataEvent : DataEvent {
    object StartLoadData : ComplexDataEvent()
    class OnLadedData(val items: List<DskComplex>) : ComplexDataEvent()
    object StartFilters : ComplexDataEvent()
    class OnLadedFilters(val filters: Filters) : ComplexDataEvent()
    class OnFilterComplex(val listOfComplex: List<DskComplex>) : ComplexDataEvent()
    class OnPriceRangeChangedProcess(val listOfComplex: List<DskComplex>) : ComplexDataEvent()
}

class ComplexViewModel(
    private val repository: ComplexRepository
) : BaseViewModel<ViewState>() {

    init {
        processDataEvent(ComplexDataEvent.StartFilters)
        processDataEvent(ComplexDataEvent.StartLoadData)


    }

    override fun initialViewState() = ViewState(
        rooms = RoomsType.values().map {
            Room(type = it)
        })


    override fun reduce(event: Event): ViewState = when (event) {
        is ComplexUiEvent -> dispatchUiEvent(event)
        is ComplexDataEvent -> dispatchDataEvent(event)
        else -> previousState
    }

    private fun dispatchUiEvent(uiEvent: ComplexUiEvent) = when (uiEvent) {
        is ComplexUiEvent.OnRoomClicked -> {
            val newRooms = previousState.rooms.toMutableList()
            newRooms[uiEvent.room.type.ordinal] = uiEvent.room

            val filterRooms = newRooms.dropLast(1).filter { it.isPressed }.map {
                it.type.ordinal
            }

            val filters = Filters(
                previousState.areaRange,
                previousState.priceRange,
                previousState.quarters,
                filterRooms
            )

            subscribeFilterList(filters)
            previousState.copy(rooms = newRooms)
        }
        is ComplexUiEvent.OnAreaRangeChanged -> {
            val areaRange = previousState.areaRange.copy(range = uiEvent.range)
            val filters = Filters(
                areaRange,
                previousState.priceRange,
                previousState.quarters
            )
            subscribeFilterList(filters)
            previousState.copy(areaRange = areaRange)
        }
        is ComplexUiEvent.OnPriceRangeChanged -> {
            val priceRange = previousState.priceRange.copy(range = uiEvent.range)
            val filters = Filters(
                previousState.areaRange,
                priceRange,
                previousState.quarters
            )
            subscribeFilterList(filters)
            previousState.copy(priceRange = priceRange)
        }
    }

    override fun onHandleErrorEvent(event: ErrorEvent): ViewState {
        return previousState
    }

    private fun dispatchDataEvent(dataEvent: ComplexDataEvent) = when (dataEvent) {
        ComplexDataEvent.StartLoadData -> {
            repository.getComplex().subscribe {
                when (it) {
                    ComplexStatus.InProgress -> {}
                    is ComplexStatus.Success -> {
                        processDataEvent(ComplexDataEvent.OnLadedData(it.data))
                    }
                    is ComplexStatus.Error -> {

                    }
                }
            }
            previousState
        }
        is ComplexDataEvent.OnLadedData -> {
            previousState.copy(complexes = dataEvent.items)
        }
        ComplexDataEvent.StartFilters -> {
            repository.filters.subscribe { processDataEvent(ComplexDataEvent.OnLadedFilters(it)) }
            previousState
        }
        is ComplexDataEvent.OnLadedFilters -> {

            val quarterGenerator: QuarterGenerator = QuarterGenerator.Base()

            val visibleBuildQuarters: List<String> =
                listOf("Все", "Сдан") + quarterGenerator.generationWithStart(
                    startQuarter = dataEvent.filters.buildQuarter.quarters.first().first,
                    year = dataEvent.filters.buildQuarter.quarters.first().second
                ) + dataEvent.filters.buildQuarter.quarters.drop(1)
                    .flatMap { quarterGenerator.simpleGeneration(it.second) }

            previousState.copy(
                areaRange = dataEvent.filters.areaRange,
                priceRange = dataEvent.filters.priceRange,
                quarters = dataEvent.filters.buildQuarter,
                builds = visibleBuildQuarters
            )
        }
        is ComplexDataEvent.OnFilterComplex -> {
            previousState.copy(complexes = dataEvent.listOfComplex)
        }
        is ComplexDataEvent.OnPriceRangeChangedProcess -> {
            previousState.copy(complexes = dataEvent.listOfComplex)
        }
    }

    private fun subscribeFilterList(filters: Filters) {
        repository.filtering(filters).subscribe({
            processDataEvent(ComplexDataEvent.OnFilterComplex(it))
        }, {})
    }
}

class ComplexViewModelFactory : ViewModelProvider.Factory {

    private val filterProcessor = FilterProcessor.Base()
    private val fakeSourceProcessor = FakeSourceProcessor.Base()
    private val repository: ComplexRepository =
        ComplexRepository.Base(fakeSourceProcessor, filterProcessor)


    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ComplexViewModel(repository) as T
    }
}