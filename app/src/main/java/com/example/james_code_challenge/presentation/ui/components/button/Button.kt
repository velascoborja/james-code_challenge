package com.example.james_code_challenge.presentation.ui.components.button

import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview(showBackground = true)
fun FavouriteButton() { // Pass in favourited items
    val itemId = "JIMMY" // TODO use local list which observes DB
    val selectedItems = remember { mutableStateListOf<String>() }
    val isSelected = selectedItems.contains(itemId) // Check if item is selected

    val colour = if (isSelected) {
        Color.Red // Filled star for selected item
    } else {
        Color.LightGray // Empty star for unselected item
    }

    IconButton(
        onClick = {
            val newIsSelected = !isSelected // Toggle selection state
            if (newIsSelected) {
                selectedItems.add(itemId)
            } else {
                selectedItems.remove(itemId)
            }
        },
        modifier = Modifier.size(32.dp)
//        , modifier = Modifier.padding(0.dp) // Remove padding // DOESNT DO ANYTHTIN
    ) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = "Favourite button",
            tint = colour
        )
    }
}