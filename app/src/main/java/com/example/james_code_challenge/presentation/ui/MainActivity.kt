package com.example.james_code_challenge.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.james_code_challenge.Constants.Companion.FAVOURITES_SCREEN
import com.example.james_code_challenge.Constants.Companion.PROCEDURES_SCREEN
import com.example.james_code_challenge.presentation.navigation.BottomNavigation
import com.example.james_code_challenge.presentation.ui.components.ProcedureDetailCard
import com.example.james_code_challenge.presentation.ui.theme.Jamescode_challengeTheme
import com.example.james_code_challenge.viewmodel.MainViewModel
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

@Composable
@Preview(showBackground = true)
fun ProcedureListScreen(
    viewModel: MainViewModel = hiltViewModel<MainViewModel>()
) {

    LazyColumn(
        modifier = Modifier // TODO
            .fillMaxHeight()
    ) {
        items(
            items = listOf(
                "James",
                "James2",
                "James3",
                "James4",
                "James5",
                "James6"
            ),
            key = { it } // TODO use UUIDs
        ) {
            ProcedureDetailCard(it)
        }
    }

}

@Composable
@Preview(showBackground = true)
fun FavouritesScreen(
    viewModel: MainViewModel = hiltViewModel<MainViewModel>()
) {

    LazyColumn(
        modifier = Modifier // TODO
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
            ProcedureDetailCard(it)
        }
    }

}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    Jamescode_challengeTheme {
//        // TODO
//    }
//}