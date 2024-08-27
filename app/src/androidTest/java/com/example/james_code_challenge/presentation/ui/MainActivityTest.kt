package com.example.james_code_challenge.presentation.ui

import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.james_code_challenge.data.model.Procedure
import com.example.james_code_challenge.mock.MockData
import com.example.james_code_challenge.presentation.ui.MainActivity.Companion.PROGRESS_ICON_TEST_TAG
import com.example.james_code_challenge.presentation.ui.screens.ProceduresScreen
import com.example.james_code_challenge.presentation.viewmodel.MainViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MainActivityTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val errorScreenMessage = "Something went wrong, please try again later"

    private val mockProcedureList = mutableListOf(
        MockData.procedureMock,
        MockData.procedureMock.copy(uuid = "1"),
        MockData.procedureMock.copy(uuid = "2"),
        MockData.procedureMock.copy(uuid = "3"),
        MockData.procedureMock.copy(uuid = "4")
    )

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testProceduresScreen_loadingState_ShowsLoading() {
        composeTestRule.activity.setContent {
            ProcedureScreenUnderTest(
                uiState = MainViewModel.ProceduresState(isLoading = true)
            )
        }

        // Shows progress icon
        composeTestRule.onNodeWithTag(PROGRESS_ICON_TEST_TAG).assertIsDisplayed()

        composeTestRule.activity.setContent {
            ProcedureScreenUnderTest(
                uiState = MainViewModel.ProceduresState(isLoading = false)
            )
        }

        // Shows correct tab after swapping
        composeTestRule.onNodeWithTag(PROGRESS_ICON_TEST_TAG).assertIsNotDisplayed()
    }

    @Test
    fun testProceduresScreen_errorState_showsCorrectText() {
        composeTestRule.activity.setContent {
            ProcedureScreenUnderTest(
                uiState = MainViewModel.ProceduresState(error = "notNull")
            )
        }

        // Shows progress icon
        composeTestRule.onNodeWithText(errorScreenMessage).assertIsDisplayed()

        composeTestRule.activity.setContent {
            ProcedureScreenUnderTest(
                uiState = MainViewModel.ProceduresState(error = null)
            )
        }

        // Shows correct tab after swapping
        composeTestRule.onNodeWithTag(PROGRESS_ICON_TEST_TAG).assertIsNotDisplayed()
    }

    @Composable
    fun ProcedureScreenUnderTest(uiState: MainViewModel.ProceduresState) {
        ProceduresScreen(
            uiState = uiState,
            fetchProcedureDetailEvent = {},
            onFavouriteToggleEvent = {},
            fetchProceduresList = {},
            fetchFavourites = {},
            isFavourite = { true }
        )
    }

    // TODO missing UI test in notes
    // TODO Skipping on FavouritesScreen UI tests, hopefully above demonstrates what I would have done

}