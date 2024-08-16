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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.james_code_challenge.Constants.Companion.FAVOURITES_SCREEN
import com.example.james_code_challenge.Constants.Companion.PROCEDURES_SCREEN
import com.example.james_code_challenge.R
import com.example.james_code_challenge.data.model.Card
import com.example.james_code_challenge.data.model.Icon
import com.example.james_code_challenge.data.model.Phase
import com.example.james_code_challenge.data.model.Procedure
import com.example.james_code_challenge.presentation.navigation.BottomNavigation
import com.example.james_code_challenge.presentation.ui.components.PhaseBottomSheet
import com.example.james_code_challenge.presentation.ui.components.ProcedureDetailCard
import com.example.james_code_challenge.presentation.ui.theme.Jamescode_challengeTheme
import com.example.james_code_challenge.presentation.viewmodel.MainViewModel
import com.example.james_code_challenge.data.model.ProcedureDetail
import com.example.james_code_challenge.util.toLocalDate
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
}

@Composable
fun ProceduresApp() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = PROCEDURES_SCREEN,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = PROCEDURES_SCREEN) { ProcedureListScreen() }
            composable(route = FAVOURITES_SCREEN) { FavouritesScreen() }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProcedureListScreen(
    modifier: Modifier = Modifier
) {
    val viewModel = hiltViewModel<MainViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {
        if (showBottomSheet && uiState.selectedProcedureDetail != null) {
            ModalBottomSheet(
                modifier = modifier.fillMaxHeight(1.0f),
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                Column(
                    modifier = modifier.padding(vertical = 100.dp) // TODO vertically center instead
                ) {
                    PhaseBottomSheet(procedureDetail = uiState.selectedProcedureDetail)
                }
            }
        }

        if (uiState.isLoading) {
            CircularProgressIndicator(modifier = modifier.align(Alignment.Center))
        } else if (uiState.error != null) {
            println("Failed to getProcedureList -> ${uiState.error}") // Production apps should use Timber
            Text(
                text = stringResource(R.string.generic_error_message),
                modifier = modifier.align(Alignment.Center)
            )
        } else {
            ProceduresList(procedureList = uiState.items, onClickEvent = {
                showBottomSheet = true
            })
        }
    }
}

@Composable
fun ProceduresList(
    modifier: Modifier = Modifier,
    procedureList: List<Procedure>,
    onClickEvent: () -> Unit,
    viewModel: MainViewModel = hiltViewModel<MainViewModel>()
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
    ) {
        items(
            items = procedureList,
            key = { it.uuid }
        ) {
            ProcedureDetailCard(it, onClickEvent = {
                viewModel.fetchProcedureDetail(it.uuid)
                onClickEvent()
            })
        }
    }
}

@Composable
fun FavouritesScreen(
    modifier: Modifier = Modifier
) {
//    val viewModel = hiltViewModel<MainViewModel>()
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
    ) {
        items(
            items = listOf(
                "Favourites",
                "Favourites2",
                "Favourites3",
                "Favourites4",
                "Favourites5",
                "Favourites6"
            ),
            key = { it } // TODO use UUIDs
        ) {
//            ProcedureDetailCard(it)
            Text(")")
        }
    }

}

@Composable
@Preview(showBackground = true)
fun ProcedureListScreenPreview() {
    ProcedureListScreen()
}

@Composable
@Preview(showBackground = true)
fun FavouritesScreenPreview() {
    FavouritesScreen()
}
