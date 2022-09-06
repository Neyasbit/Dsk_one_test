package com.example.feature_complexs

import android.util.Log
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
import com.example.core_model.DskComplex
import com.example.core_model.Filters
import com.example.core_model.PriceRange
import com.example.core_model.Room
import com.example.core_model.RoomsType
import org.threeten.bp.LocalDate

data class ViewState(
    val complexes: List<DskComplex> = emptyList(),
    val rooms: List<Room> = emptyList(),
    val filters: Filters = Filters(),
    val isVisibleFilters: Boolean = false
)

sealed class ComplexUiEvent : UiEvent {
    data class OnRoomClicked(val room: Room) : ComplexUiEvent()
    data class OnAreaRangeChanged(val range: ClosedFloatingPointRange<Float>) : ComplexUiEvent()
    data class OnPriceRangeChanged(val range: ClosedFloatingPointRange<Float>) : ComplexUiEvent()
    data class OnQuartersChanged(val sortedDate: Pair<String, LocalDate>) : ComplexUiEvent()
    class VisibilityFilters(val isVisible: Boolean) : ComplexUiEvent()
    object ResetFilters : ComplexUiEvent()
}

sealed class ComplexDataEvent : DataEvent {
    object StartLoadData : ComplexDataEvent()
    class OnLadedData(val items: List<DskComplex>) : ComplexDataEvent()
    object StartFilters : ComplexDataEvent()
    class OnLadedFilters(val filters: Filters) : ComplexDataEvent()
    class OnFilterComplex(val listOfComplex: List<DskComplex>) : ComplexDataEvent()
}

class ComplexViewModel(
    private val repository: ComplexRepository
) : BaseViewModel<ViewState>() {

    init {
        processDataEvent(ComplexDataEvent.StartFilters)
        processDataEvent(ComplexDataEvent.StartLoadData)
    }

    override fun initialViewState() = ViewState(
        rooms = RoomsType.values().map { Room(type = it) })


    override fun reduce(event: Event): ViewState = when (event) {
        is ComplexUiEvent -> dispatchUiEvent(event)
        is ComplexDataEvent -> dispatchDataEvent(event)
        else -> previousState
    }

    private fun dispatchUiEvent(uiEvent: ComplexUiEvent) = when (uiEvent) {
        is ComplexUiEvent.OnRoomClicked -> {
            val newRooms = previousState.rooms.toMutableList()
            newRooms[uiEvent.room.type.ordinal] = uiEvent.room

            val filterRooms = newRooms
                .dropLast(1)
                .filter { it.isPressed }
                .map { it.type.ordinal }

            val roomFilter = previousState.filters.copy(rooms = filterRooms)
            subscribeFilterList(roomFilter)
            previousState.copy(rooms = newRooms, filters = roomFilter)
        }
        is ComplexUiEvent.OnAreaRangeChanged -> {
            val areaRange = previousState.filters.areaRange.copy(range = uiEvent.range)
            val areaFilter = previousState.filters.copy(areaRange = areaRange)
            subscribeFilterList(areaFilter)
            previousState.copy(filters = areaFilter)
        }
        is ComplexUiEvent.OnPriceRangeChanged -> {
            val priceRange = previousState.filters.priceRange.copy(range = uiEvent.range)
            val priceFilter = previousState.filters.copy(priceRange = priceRange)
            subscribeFilterList(priceFilter)
            previousState.copy(filters = priceFilter)
        }
        is ComplexUiEvent.OnQuartersChanged -> {
            val quarterFilter = previousState.filters.copy(sortedDate = uiEvent.sortedDate)
            subscribeFilterList(quarterFilter)
            previousState.copy(filters = quarterFilter)
        }
        is ComplexUiEvent.VisibilityFilters -> previousState.copy(isVisibleFilters = uiEvent.isVisible)
        is ComplexUiEvent.ResetFilters -> {
            //todo make
            previousState
        }
    }

    override fun onHandleErrorEvent(event: ErrorEvent): ViewState {
        return previousState
    }

    private fun dispatchDataEvent(dataEvent: ComplexDataEvent) = when (dataEvent) {
        //todo one added one request for filters(after pressed show)
        ComplexDataEvent.StartLoadData -> {
            repository.getComplex().subscribe {
                when (it) {
                    ComplexStatus.InProgress -> {
                        //todo progress
                    }
                    is ComplexStatus.Success -> {
                        processDataEvent(ComplexDataEvent.OnLadedData(it.data))
                    }
                    is ComplexStatus.Error -> {
                        //todo error handler
                    }
                }
            }
            previousState
        }
        is ComplexDataEvent.OnLadedData -> {
            val initRooms: List<Int> =
                dataEvent.items.map { it.rooms }.flatten().toSet().toList()
            val filters = previousState.filters.copy(rooms = initRooms)
            previousState.copy(
                complexes = dataEvent.items,
                filters = filters
            )
        }
        ComplexDataEvent.StartFilters -> {
            repository.filters.subscribe(
                { processDataEvent(ComplexDataEvent.OnLadedFilters(it)) },
                {
                    Log.e("TAG", "dispatchDataEvent: ${it.message}")
                })
            previousState
        }
        is ComplexDataEvent.OnLadedFilters -> {
            previousState.copy(filters = dataEvent.filters)
        }
        is ComplexDataEvent.OnFilterComplex -> {
            previousState.copy(complexes = dataEvent.listOfComplex)
        }
    }

    private fun subscribeFilterList(filters: Filters) {
        repository.filtering(filters).subscribe({
            processDataEvent(ComplexDataEvent.OnFilterComplex(it))
        }, {
            //todo error handling
        })
    }
}

class ComplexViewModelFactory : ViewModelProvider.Factory {
    //todo added di
    private val filterProcessor = FilterProcessor.Base()
    private val fakeSourceProcessor = FakeSourceProcessor.Base()
    private val repository: ComplexRepository =
        ComplexRepository.Base(fakeSourceProcessor, filterProcessor)


    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ComplexViewModel(repository) as T
    }
}