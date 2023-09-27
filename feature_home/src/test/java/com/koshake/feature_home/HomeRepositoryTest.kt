package com.koshake.feature_home

import app.cash.turbine.test
import com.koshake.feature_home.data.api.IHomeApi
import com.koshake.feature_home.data.repository.HomeRepositoryImpl
import com.koshake.feature_home.domain.HomeRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class HomeRepositoryTest {

    private val homeApi: IHomeApi = mock()

    private lateinit var homeRepository: HomeRepository

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun init() {
        homeRepository = HomeRepositoryImpl(homeApi)
    }

    @Test
    fun `homeRepository returns Loading at start`() {
        runTest {
            whenever(homeApi.getRandomQuote()).thenReturn(RandomQuoteFactory.getRandomQuoteResponse())
            homeRepository.getRandomQuote().test {
                assertEquals(com.koshake.core_api.base.Result.Loading, awaitItem())
                cancelAndIgnoreRemainingEvents()
            }

        }
    }

    @Test
    fun `homeRepository returns Content data`() {
        runTest {
            whenever(homeApi.getRandomQuote()).thenReturn(RandomQuoteFactory.getRandomQuoteResponse())
            homeRepository.getRandomQuote()
                .test {
                    assertEquals(com.koshake.core_api.base.Result.Content(RandomQuoteFactory.getRandomQuoteEntity()), expectMostRecentItem())
                }

        }
    }

    @Test
    fun `homeRepository returns Error after api throws Exception`() {
        runTest {
            val exception = RuntimeException("error!")
            whenever(homeApi.getRandomQuote()).thenThrow(exception)
            homeRepository.getRandomQuote()
                .test {
                    assertEquals(com.koshake.core_api.base.Result.Error(exception), expectMostRecentItem())
                }
        }
    }
}
