package com.koshake.feature_list

import com.koshake.core_api.base.Result
import com.koshake.feature_list.domain.repository.HousesListRepository
import com.koshake.feature_list.ui.HousesListScreenViewModel
import junit.framework.TestCase
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

class HousesListScreenViewModelTest {
    private val housesListRepository: HousesListRepository = mock()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    lateinit var viewModel: HousesListScreenViewModel

    @Before
    fun init() {
        viewModel = HousesListScreenViewModel(housesListRepository)
    }

    @Test
    fun `getHousesList executed ones after viewModel init`() {
        val housesListEntity = HousesListDataFactory.getHousesListEntity()
        runTest {
            whenever(housesListRepository.getHousesList()).thenReturn(flow { emit(housesListEntity) }.map { Result.Content(it) })
            verify(housesListRepository, Mockito.times(1)).getHousesList()
        }
    }

    @Test
    fun `getHousesList executed 2 time after onRefresh and viewModel init`() {
        val housesList = HousesListDataFactory.getHousesListEntity()
        runTest {
            whenever(housesListRepository.getHousesList()).thenReturn(flow { emit(housesList) }.map { Result.Content(it) })
            viewModel.onRefresh()
            verify(housesListRepository, Mockito.times(2)).getHousesList()
        }
    }

    @Test
    fun `viewModel state after success returns the same data`() {
        val housesList = HousesListDataFactory.getHousesListEntity()
        runTest {
            whenever(housesListRepository.getHousesList()).thenReturn(flow { emit(housesList) }.map { Result.Content(it) })
            viewModel.onRefresh()
            TestCase.assertEquals(viewModel.state.housesList, HousesListDataFactory.getHousesItemsList())
        }
    }

    @Test
    fun `state isError returns true after onRefresh throws exception`() {
        runTest {
            whenever(housesListRepository.getHousesList()).thenReturn(flow { emit(Result.Error(Exception())) })
            viewModel.onRefresh()
            TestCase.assertEquals(viewModel.state.isError, true)
        }
    }

}
