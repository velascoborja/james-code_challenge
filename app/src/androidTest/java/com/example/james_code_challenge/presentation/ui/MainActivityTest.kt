package com.example.james_code_challenge.presentation.ui

import android.view.KeyEvent.ACTION_DOWN
import android.view.accessibility.AccessibilityNodeInfo
import androidx.activity.compose.setContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.key.KeyEvent

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.dp
import androidx.test.espresso.Espresso
import androidx.test.platform.app.InstrumentationRegistry
import com.example.james_code_challenge.mock.MockData
import com.example.james_code_challenge.mock.MockData.Companion.procedureDetailMock
import com.example.james_code_challenge.mock.MockData.Companion.procedureMock
import com.example.james_code_challenge.presentation.ui.MainActivity.Companion.PROCEDURE_LIST_TEST_TAG
import com.example.james_code_challenge.presentation.ui.MainActivity.Companion.PROGRESS_ICON_TEST_TAG
import com.example.james_code_challenge.presentation.ui.components.BottomSheet.Companion.BOTTOM_SHEET_TEST_TAG
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
    fun testProceduresListScreen_loadingState_ShowsLoading() {
        composeTestRule.activity.setContent {
            ProceduresListScreen(
                uiState = MainViewModel.ProceduresListState(isLoading = true),
                fetchProcedureDetailEvent = {})
        }

        // Shows progress icon
        composeTestRule.onNodeWithTag(PROGRESS_ICON_TEST_TAG).assertIsDisplayed()

        composeTestRule.activity.setContent {
            ProceduresListScreen(
                uiState = MainViewModel.ProceduresListState(isLoading = false),
                fetchProcedureDetailEvent = {})
        }

        // Shows correct tab after swapping
        composeTestRule.onNodeWithTag(PROGRESS_ICON_TEST_TAG).assertIsNotDisplayed()
    }

    @Test
    fun testProceduresListScreen_errorState_showsCorrectText() {
        composeTestRule.activity.setContent {
            ProceduresListScreen(
                uiState = MainViewModel.ProceduresListState(error = "notNull"),
                fetchProcedureDetailEvent = {})
        }

        // Shows progress icon
        composeTestRule.onNodeWithText(errorScreenMessage).assertIsDisplayed()

        composeTestRule.activity.setContent {
            ProceduresListScreen(
                uiState = MainViewModel.ProceduresListState(error = null),
                fetchProcedureDetailEvent = {})
        }

        // Shows correct tab after swapping
        composeTestRule.onNodeWithTag(PROGRESS_ICON_TEST_TAG).assertIsNotDisplayed()
    }

}