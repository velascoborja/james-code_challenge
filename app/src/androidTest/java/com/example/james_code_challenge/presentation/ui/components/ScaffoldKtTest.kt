package com.example.james_code_challenge.presentation.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.james_code_challenge.mock.MockData
import com.example.james_code_challenge.presentation.ui.MainActivity.Companion.FAVOURITES_TEST_TAG
import com.example.james_code_challenge.presentation.ui.MainActivity.Companion.PROCEDURE_LIST_TEST_TAG
import com.example.james_code_challenge.presentation.ui.screens.FavouritesScreen
import com.example.james_code_challenge.presentation.ui.screens.ProceduresScreen
import com.example.james_code_challenge.presentation.viewmodel.MainViewModel
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ScaffoldKtTest {

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    private val errorScreenMessage = "Something went wrong, please try again later"

    @Test
    fun testScaffold_showsCorrectTab_canSuccessfullySwapBetween() {
        composeTestRule.setContent {
            ProceduresScaffold(
                {
                    ProceduresScreen(
                        uiState = MainViewModel.ProceduresState(items = listOf(MockData.procedureMock)),
                        fetchProcedureDetailEvent = {},
                        onFavouriteToggleEvent = {},
                        fetchProceduresAndFavourites = {},
                        isFavourite = { true }
                    )
                },
                {
                    FavouritesScreen(
                        uiState = MainViewModel.FavouritesState(
                            listOf(
                                MockData.procedureMock,
                                MockData.procedureMock.copy(uuid = "1")
                            )
                        ),
                        onFavouriteToggleEvent = {},
                        fetchFavourites = {},
                        onFetchProcedureDetailEvent = {},
                        isFavourite = { true }
                    )
                }
            )
        }

        // Shows correct tab on startup
        composeTestRule.onNodeWithTag(PROCEDURE_LIST_TEST_TAG).assertIsDisplayed()
        composeTestRule.onNodeWithTag(FAVOURITES_TEST_TAG).assertIsNotDisplayed()

        composeTestRule.onNodeWithTag(errorScreenMessage).assertIsNotDisplayed()

        // Swap to other tab
        composeTestRule.onNodeWithText("Favourites").performClick()

        // Shows correct tab after swapping
        composeTestRule.onNodeWithTag(PROCEDURE_LIST_TEST_TAG).assertIsNotDisplayed()
        composeTestRule.onNodeWithTag(FAVOURITES_TEST_TAG).assertIsDisplayed()

        composeTestRule.onNodeWithTag(errorScreenMessage).assertIsNotDisplayed()
    }
}