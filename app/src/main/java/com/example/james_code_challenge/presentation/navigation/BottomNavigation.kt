package com.example.james_code_challenge.presentation.navigation

import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.example.james_code_challenge.Constants.Companion.FAVOURITES_SCREEN
import com.example.james_code_challenge.Constants.Companion.PROCEDURES_SCREEN

@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavItem(
            name = "Procedures", // TODO Rename & extract to String on fresh commit when ready
            route = PROCEDURES_SCREEN,
            icon = Icons.Default.Home
        ),
        BottomNavItem(
            name = "Favourites",
            route = FAVOURITES_SCREEN,
            icon = Icons.Default.Star
        )
    )

    var selectedItem by remember { mutableStateOf(items.first()) }

    BottomAppBar {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = item.name) },
                label = { Text(item.name) },
                selected = selectedItem == item,
                onClick = {
                    selectedItem = item
                    navController.navigate(item.route)
                }
            )
        }
    }
}

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector
)