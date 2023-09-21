package com.koshake.viewmodel_base.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class ViewModelAssistedFactory @Inject constructor(
    @ViewModelQualifier private val factories: Map<Class<out Any>, @JvmSuppressWildcards Provider<Any>>,
) {

    inline fun <reified T : Any> assistedViewModelFactory(): T = assistedViewModelFactory(T::class.java)

    fun <T : Any> assistedViewModelFactory(factoryClass: Class<T>): T {
        val provider = factories[factoryClass]
            ?: throw IllegalArgumentException("Binding for $factoryClass not found.")

        @Suppress("UNCHECKED_CAST")
        return provider.get() as T
    }
}

@Composable
inline fun <reified VM : ViewModel> assistedViewModel(
    crossinline createViewModel: () -> VM,
): VM {
    val factory = remember { createSingleViewModelFactory(createViewModel) }
    return viewModel(factory = factory)
}

inline fun createSingleViewModelFactory(
    crossinline createViewModel: () -> ViewModel,
): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T = createViewModel.invoke() as T
    }
}
