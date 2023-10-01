package com.koshake.gameofthronescompose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.koshake.feature_home.navigation.HomeNavGraphHandlerImpl
import com.koshake.feature_list.navigation.ListNavGraphHandlerImpl
import com.koshake.main.ui.MainScreen
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val homeNavGraphHandler =  HomeNavGraphHandlerImpl()

    private val listNavGraphHandler = ListNavGraphHandlerImpl()

    @Test
    fun quoteTextIsVisible() {
        composeTestRule.setContent {
            com.koshake.koshake.core_ui.ui.theme.GameOfThronesComposeTheme {
                MainScreen(
                    homeNavGraphHandler = homeNavGraphHandler,
                    listNavGraphHandler = listNavGraphHandler
                )
            }
        }

        composeTestRule.runOnIdle {
            composeTestRule.onNode(hasTestTag(com.koshake.feature_home.utils.TestTags.HOME_CHARACTER_NAME_TEXT), useUnmergedTree = true)
                .assertIsDisplayed()

            composeTestRule.onNode(hasTestTag(com.koshake.feature_home.utils.TestTags.HOME_CHARACTER_QUOTE_TEXT), useUnmergedTree = true)
                .assertIsDisplayed()
        }
    }
}
