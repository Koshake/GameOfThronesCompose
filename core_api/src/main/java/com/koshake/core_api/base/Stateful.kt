package com.koshake.core_api.base

import kotlinx.coroutines.flow.StateFlow

interface Stateful<State : Any> {

    val stateFlow: StateFlow<State>
    val state: State

    fun updateState(transform: State.() -> State)
}
