package com.example.james_code_challenge.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.james_code_challenge.R
import com.example.james_code_challenge.data.model.FavouriteItem
import com.example.james_code_challenge.data.model.Procedure
import com.example.james_code_challenge.mock.MockData
import com.example.james_code_challenge.presentation.ui.components.Card.Companion.IMAGE_CONTENT_DESCRIPTION

class Card {
    companion object {
        const val IMAGE_CONTENT_DESCRIPTION = "Procedure image"
    }
}

@Composable
fun ProcedureDetailCard(
    modifier: Modifier = Modifier,
    procedure: Procedure,
    favouritesList: List<Procedure>,
    onCardClickEvent: () -> Unit,
    onFavouriteToggleEvent: (FavouriteItem) -> Unit,
    isFavourite: (String) -> Boolean
) {
    LaunchedEffect(favouritesList) {} // Trigger recomposition when favourites change

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                onCardClickEvent() // Trigger bottomsheet
            }
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(procedure.icon.iconUrl)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    modifier = modifier
                        .size(120.dp)
                        .semantics {
                            contentDescription = IMAGE_CONTENT_DESCRIPTION
                        }
                )

                Column(
                    modifier = modifier
                        .weight(1f)
                        .padding(horizontal = 5.dp)
                ) {
                    Text(
                        text = procedure.name,
                        fontWeight = FontWeight.Bold,
                        modifier = modifier.padding(bottom = 10.dp)
                    )
                    Text(text = "Phase Count: ${procedure.phases.size}")
                }

                FavouriteButton(
                    onClickEvent = {
                        onFavouriteToggleEvent(FavouriteItem(procedure.uuid, procedure))
                    },
                    currentItemUuid = procedure.uuid,
                    favouritesList = favouritesList,
                    isFavourite = { isFavourite(procedure.uuid) }
                )
            }
        }
    }
}


@Composable
@Preview()
fun ProcedureDetailCardPreview() {
    ProcedureDetailCard(
        procedure = MockData.procedureMock,
        onCardClickEvent = {},
        onFavouriteToggleEvent = {},
        favouritesList = listOf(MockData.procedureMock),
        isFavourite = { true }
    )
}