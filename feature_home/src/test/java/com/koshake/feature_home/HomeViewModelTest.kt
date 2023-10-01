package com.koshake.feature_home

import com.koshake.core_api.base.Result
import com.koshake.feature_home.domain.HomeRepository
import com.koshake.feature_home.ui.HomeViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class HomeViewModelTest {

    private val homeRepository: HomeRepository = mock()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    lateinit var viewModel: HomeViewModel

    @Before
    fun init() {
        viewModel = HomeViewModel(homeRepository)
    }

    @Test
    fun `getRandomQuote executed ones after viewModel init`() {
        val randomQuote = RandomQuoteFactory.getRandomQuoteEntity()
        runTest {
            whenever(homeRepository.getRandomQuote()).thenReturn(flow { emit(randomQuote) }.map { Result.Content(it) })
            verify(homeRepository, Mockito.times(1)).getRandomQuote()
        }

    }

    @Test
    fun `getRandomQuote executed 2 time after onRefresh and viewModel init`() {
        val randomQuote = RandomQuoteFactory.getRandomQuoteEntity()
        runTest {
            whenever(homeRepository.getRandomQuote()).thenReturn(flow { emit(randomQuote) }.map { Result.Content(it) })
            viewModel.onRefresh()
            verify(homeRepository, Mockito.times(2)).getRandomQuote()
        }
    }

    @Test
    fun `viewModel state after success returns the same data`() {
        val randomQuote = RandomQuoteFactory.getRandomQuoteEntity()
        runTest {
            whenever(homeRepository.getRandomQuote()).thenReturn(flow { emit(randomQuote) }.map { Result.Content(it) })
            viewModel.onRefresh()
            assertEquals(viewModel.state.randomQuoteUi, RandomQuoteFactory.getRandomQuoteUi())
        }
    }

    @Test
    fun `state isError returns true after onRefresh throws exception`() {
        runTest {
            whenever(homeRepository.getRandomQuote()).thenReturn(flow { emit(Result.Error(Exception())) })
            viewModel.onRefresh()
            assertEquals(viewModel.state.isError, true)
        }
    }
}
