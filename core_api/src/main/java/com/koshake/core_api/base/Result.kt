package com.koshake.core_api.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

sealed interface ResultState {

    val isLoading: Boolean
    val isContent: Boolean
    val isError: Boolean

    fun errorOrNull(): Throwable?

    sealed interface Loading : ResultState

    sealed interface Content : ResultState

    sealed interface Error : ResultState {
        val error: Throwable
    }
}

sealed class Result<out C> : ResultState {

    override val isLoading: Boolean get() = this is Loading
    override val isContent: Boolean get() = this is Content
    override val isError: Boolean get() = this is Error

    fun contentOrNull(): C? = (this as? Content)?.value
    override fun errorOrNull(): Throwable? = (this as? Error)?.error

    object Loading : Result<Nothing>(), ResultState.Loading {
        override fun toString(): String = "Loading"
    }

    data class Content<C>(val value: C) : Result<C>(), ResultState.Content
    data class Error(override val error: Throwable) : Result<Nothing>(), ResultState.Error
}

private inline fun <T : ResultState, reified R> Flow<T>.onEachLceType(crossinline onType: suspend (R) -> Unit): Flow<T> {
    return onEach { lce -> if (lce is R) onType(lce) }
}

fun <T> Flow<Result<T>>.onEachContent(onContent: suspend (T) -> Unit): Flow<Result<T>> {
    return onEachLceType<Result<T>, Result.Content<T>> { onContent(it.value) }
}

fun <T : ResultState> Flow<T>.onEachError(onError: suspend (Throwable) -> Unit): Flow<T> {
    return onEachLceType<T, ResultState.Error> { onError(it.error) }
}

