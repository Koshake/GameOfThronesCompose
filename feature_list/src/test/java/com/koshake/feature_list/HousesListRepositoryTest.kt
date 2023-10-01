package com.koshake.feature_list

import app.cash.turbine.test
import com.koshake.core_api.base.Result
import com.koshake.feature_list.data.api.IListApi
import com.koshake.feature_list.data.repository.HousesListRepositoryImpl
import com.koshake.feature_list.domain.repository.HousesListRepository
import junit.framework.TestCase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class HousesListRepositoryTest {

    private val listApi: IListApi = mock()

    private lateinit var housesListRepository: HousesListRepository

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun init() {
        housesListRepository = HousesListRepositoryImpl(listApi)
    }

    @Test
    fun `housesListRepository returns Loading at start`() {
        runTest {
            whenever(listApi.getHouses()).thenReturn(HousesListDataFactory.getHousesListResponse())
            housesListRepository.getHousesList().test {
                TestCase.assertEquals(Result.Loading, awaitItem())
                cancelAndIgnoreRemainingEvents()
            }

        }
    }

    @Test
    fun `housesListRepository returns Content data`() {
        runTest {
            whenever(listApi.getHouses()).thenReturn(HousesListDataFactory.getHousesListResponse())
            housesListRepository.getHousesList()
                .test {
                    TestCase.assertEquals(Result.Content(HousesListDataFactory.getHousesListEntity()), expectMostRecentItem())
                }
        }
    }

    @Test
    fun `housesListRepository returns Error after api throws Exception`() {
        runTest {
            val exception = RuntimeException("error!")
            whenever(listApi.getHouses()).thenThrow(exception)
            housesListRepository.getHousesList()
                .test {
                    TestCase.assertEquals(Result.Error(exception), expectMostRecentItem())
                }
        }
    }
}
