package com.koshake.core_api.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class StatefulViewModel<State : Any>(
    initialState: State
) : ViewModel(), Stateful<State> {

    final override val stateFlow: StateFlow<State> get() = _stateFlow.asStateFlow()
    final override val state: State get() = stateFlow.value

    private val _stateFlow by lazy { MutableStateFlow(initialState) }

    final override fun updateState(transform: State.() -> State) {
        _stateFlow.value = transform.invoke(state)
    }
}
