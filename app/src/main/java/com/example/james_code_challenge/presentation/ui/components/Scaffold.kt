package com.example.james_code_challenge.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.james_code_challenge.Constants.Companion.FAVOURITES_SCREEN
import com.example.james_code_challenge.Constants.Companion.PROCEDURES_SCREEN
import com.example.james_code_challenge.presentation.navigation.BottomNavigation

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProceduresScaffold(
    procedureScreen: @Composable () -> Unit,
    favouritesScreen: @Composable () -> Unit
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = PROCEDURES_SCREEN,
            modifier = Modifier
                .padding(innerPadding)
                .semantics {
                    testTagsAsResourceId = true
                }
        ) {
            composable(route = PROCEDURES_SCREEN) { procedureScreen() }
            composable(route = FAVOURITES_SCREEN) { favouritesScreen() }
        }
    }
}