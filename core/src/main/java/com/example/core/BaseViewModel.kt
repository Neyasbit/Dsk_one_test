package com.example.core

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * Одиночные ивенты: например для отображения диалога
 */
interface SingleEvent

/**
 * Ивенты, с которыми работает VM
 */
interface Event

/**
 * Ивенты, которые летят от View
 */
interface UiEvent : Event

/**
 * Ивенты, которые летят внутри VM
 */
interface DataEvent : Event

/**
 * Ивенты, которые летают между несколькими VM
 */
interface OutputEvent : Event

/**
 * Ивенты для обработки ошибок
 */
interface ErrorEvent : Event {
    val error: Throwable
}

@Suppress("TooManyFunctions")
abstract class BaseViewModel<VIEW_STATE: Any> : ViewModel() {

    private val _viewState: BehaviorSubject<VIEW_STATE> by lazy {
        BehaviorSubject.createDefault(
            initialViewState()
        )
    }
    val viewState: Observable<VIEW_STATE>
        get() = _viewState.hide()

    private val _singleEvent: Channel<SingleEvent> = Channel()
    val singleEvent = _singleEvent.receiveAsFlow()

    protected val previousState: VIEW_STATE
        get() = viewState.blockingFirst() ?: initialViewState()

    protected abstract fun initialViewState(): VIEW_STATE

    protected abstract fun reduce(event: Event): VIEW_STATE

    protected abstract fun onHandleErrorEvent(event: ErrorEvent): VIEW_STATE

    protected open fun onAfterStateChanged(newViewState: VIEW_STATE, event: Event) {
    }

    fun processUiEvent(event: UiEvent) {
        updateState(event)
    }

    protected fun processDataEvent(event: DataEvent) {
        updateState(event)
    }

    protected fun processOutputEvent(event: OutputEvent) {
        updateState(event)
    }

    protected fun sendSingleEvent(event: SingleEvent, actionAfter: () -> Unit = {}) {
        viewModelScope.launch {
            _singleEvent.send(event)
            actionAfter()
        }
    }

    private fun updateState(event: Event) {
        val newViewState = reduce(event)
        compareNewStateWithCurrentAndUpdate(newViewState, event)
    }

    protected fun processErrorEvent(errorEvent: ErrorEvent) {
        val newViewState = if (DefaultErrorDispatcher.processError(errorEvent.error)) {
            previousState
        } else {
            onHandleErrorEvent(event = errorEvent)
        }
        compareNewStateWithCurrentAndUpdate(newViewState, errorEvent)
    }

    private fun compareNewStateWithCurrentAndUpdate(newViewState: VIEW_STATE, event: Event) {
        if (newViewState != viewState.blockingFirst()) {
            _viewState.onNext(newViewState)
            onAfterStateChanged(newViewState, event)
        }
    }

    private object DefaultErrorDispatcher {

        fun processError(error: Throwable): Boolean {
            Log.e(this::class.simpleName, "processError: $error")
            return false
        }
    }
}
