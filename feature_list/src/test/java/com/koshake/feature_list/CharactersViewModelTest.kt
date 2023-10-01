package com.koshake.feature_list

import com.koshake.core_api.base.Result
import com.koshake.feature_list.domain.repository.CharactersListRepository
import com.koshake.feature_list.ui.characters.CharactersViewModel
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

class CharactersViewModelTest {

    private val charactersListRepository: CharactersListRepository = mock()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: CharactersViewModel

    @Before
    fun init() {
        viewModel = CharactersViewModel(charactersListRepository = charactersListRepository, house = CharactersListDataFactory.house)
    }

    @Test
    fun `getCharactersByHouse executed ones after viewModel init`() {
        val characters = CharactersListDataFactory.getCharactersListEntity()
        runTest {
            whenever(
                charactersListRepository.getCharactersByHouse(CharactersListDataFactory.house)
            ).thenReturn(flow { emit(characters) }.map { Result.Content(it) })
            verify(charactersListRepository, Mockito.times(1)).getCharactersByHouse(CharactersListDataFactory.house)
        }
    }

    @Test
    fun `state isError returns true after onRefresh throws exception`() {
        runTest {
            whenever(
                charactersListRepository
                    .getCharactersByHouse(CharactersListDataFactory.house)
            ).thenReturn(flow { emit(Result.Error(Exception())) })
            viewModel.onRefresh()
            TestCase.assertEquals(viewModel.state.isError, true)
        }
    }
}
