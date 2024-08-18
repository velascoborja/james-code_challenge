package com.example.james_code_challenge.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.james_code_challenge.R
import com.example.james_code_challenge.data.model.Procedure
import com.example.james_code_challenge.mock.MockData
import com.example.james_code_challenge.presentation.ui.MainActivity.Companion.FAVOURITES_TEST_TAG
import com.example.james_code_challenge.presentation.ui.MainActivity.Companion.PROCEDURE_LIST_TEST_TAG
import com.example.james_code_challenge.presentation.ui.MainActivity.Companion.PROGRESS_ICON_TEST_TAG
import com.example.james_code_challenge.presentation.ui.components.PhaseBottomSheet
import com.example.james_code_challenge.presentation.ui.components.ProcedureDetailCard
import com.example.james_code_challenge.presentation.ui.components.ProceduresScaffold
import com.example.james_code_challenge.presentation.ui.theme.Jamescode_challengeTheme
import com.example.james_code_challenge.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Jamescode_challengeTheme {
                ProceduresApp()
            }
        }
    }

    companion object {
        const val PROCEDURE_LIST_TEST_TAG = "procedure_list_test_tag"
        const val FAVOURITES_TEST_TAG = "favourites_test_tag"
        const val PROGRESS_ICON_TEST_TAG = "progress_icon_test_tag"
    }
}

@Composable
fun ProceduresApp() {
    val viewModel = hiltViewModel<MainViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProceduresScaffold(
        procedureListScreen = {
            ProceduresListScreen(
                uiState = uiState,
                fetchProcedureDetailEvent = { viewModel.fetchProcedureDetail(it) }
            )
        },
        favouritesScreen = {
            FavouritesScreen(
                procedures = listOf(
                    MockData.procedureMock,
                    MockData.procedureMock.copy(uuid = "1"),
                    MockData.procedureMock.copy(uuid = "2"),
                    MockData.procedureMock.copy(uuid = "3"),
                    MockData.procedureMock.copy(uuid = "4")
                )
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProceduresListScreen(
    modifier: Modifier = Modifier,
    uiState: MainViewModel.ProceduresListState,
    fetchProcedureDetailEvent: (String) -> Unit,
    showBottomSheet: Boolean = false
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheetState by remember { mutableStateOf(showBottomSheet) }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        if (showBottomSheetState && uiState.selectedProcedureDetail != null) {
            ModalBottomSheet(
                modifier = modifier.fillMaxHeight(1.0f),
                onDismissRequest = {
                    showBottomSheetState = false
                },
                sheetState = sheetState
            ) {
                Column(
                    modifier = modifier.padding(vertical = 100.dp)
                ) {
                    PhaseBottomSheet(procedureDetail = uiState.selectedProcedureDetail)
                }
            }
        }

        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = modifier
                    .align(Alignment.Center)
                    .testTag(PROGRESS_ICON_TEST_TAG)
            )
        } else if (uiState.error != null) {
            println("Failed to getProcedureList -> ${uiState.error}") // Production apps should use Timber
            Text(
                text = stringResource(R.string.generic_error_message),
                modifier = modifier.align(Alignment.Center)
            )
        } else {
            ProceduresList(
                procedureList = uiState.items,
                onShowBottomSheetEvent = { showBottomSheetState = true },
                onFetchProcedureDetailEvent = { fetchProcedureDetailEvent(it) }
            )
        }
    }
}

@Composable
fun ProceduresList(
    modifier: Modifier = Modifier,
    procedureList: List<Procedure>,
    onShowBottomSheetEvent: () -> Unit,
    onFetchProcedureDetailEvent: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .testTag(PROCEDURE_LIST_TEST_TAG)
    ) {
        items(
            items = procedureList,
            key = { it.uuid }
        ) {
            ProcedureDetailCard(
                procedure = it,
                onClickEvent = {
                    onShowBottomSheetEvent()
                    onFetchProcedureDetailEvent(it.uuid)
                })
        }
    }
}

@Composable
fun FavouritesScreen(
    modifier: Modifier = Modifier,
    procedures: List<Procedure>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .testTag(FAVOURITES_TEST_TAG)
    ) {
        items(
            items = procedures,
            key = { it.uuid }
        ) {
            ProcedureDetailCard(procedure = it, onClickEvent = {})
        }
    }

}

@Composable
@Preview(showBackground = true)
fun ProcedureListScreenPreview() {
    ProceduresListScreen(
        uiState = MainViewModel.ProceduresListState(
            items = listOf(
                MockData.procedureMock,
                MockData.procedureMock.copy(uuid = "1"),
                MockData.procedureMock.copy(uuid = "2"),
                MockData.procedureMock.copy(uuid = "3"),
                MockData.procedureMock.copy(uuid = "4")
            )
        ),
        fetchProcedureDetailEvent = {}
    )
}

@Composable
@Preview(showBackground = true)
fun FavouritesScreenPreview() {
    FavouritesScreen(
        procedures = listOf(
            MockData.procedureMock,
            MockData.procedureMock.copy(uuid = "1"),
            MockData.procedureMock.copy(uuid = "2"),
            MockData.procedureMock.copy(uuid = "3"),
            MockData.procedureMock.copy(uuid = "4")
        )
    )
}
