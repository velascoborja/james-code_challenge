package com.example.james_code_challenge.domain.usecase

import androidx.room.Transaction
import com.example.james_code_challenge.data.model.FavouriteItem
import com.example.james_code_challenge.data.repository.FavouritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class FavouritesUsecaseImpl @Inject constructor(
    private val repository: FavouritesRepository
) : FavouritesUsecase {

    @Transaction // Ensures atomic execution
    override suspend fun toggleFavourite(favouriteItem: FavouriteItem) {
        if (isFavourite(favouriteItem.uuid)) {
            repository.deleteFavoriteItem(favouriteItem.uuid)
        } else {
            repository.insertFavouriteItem(favouriteItem)
        }
    }

    override suspend fun isFavourite(uuid: String): Boolean {
        return repository.isFavourite(uuid)
    }

    override suspend fun getAllFavoriteItems(): Flow<List<FavouriteItem>> {
        return flowOf(repository.getAllFavoriteItems())
    }

}

interface FavouritesUsecase {
    suspend fun toggleFavourite(favouriteItem: FavouriteItem)
    suspend fun isFavourite(uuid: String): Boolean
    suspend fun getAllFavoriteItems(): Flow<List<FavouriteItem>>
}