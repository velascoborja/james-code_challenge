package com.example.james_code_challenge.data.repository

import com.example.james_code_challenge.data.database.dao.FavouriteItemDao
import com.example.james_code_challenge.data.model.FavouriteItem
import com.example.james_code_challenge.data.model.Procedure
import javax.inject.Inject

class FavouritesRepositoryImpl @Inject constructor(
    private val favouriteItemDao: FavouriteItemDao
) : FavouritesRepository {

    // Would usually place error handling for these DB operatons at this layer
    override suspend fun insertFavouriteItem(favouriteItem: FavouriteItem) {
        favouriteItemDao.insertFavoriteItem(favouriteItem)
    }

    override suspend fun isFavourite(uuid: String): Boolean {
        return favouriteItemDao.isFavourite(uuid)
    }

    override suspend fun getAllFavoriteItems(): List<FavouriteItem> { // TODO JIMMY so save actual procedure but have it empty..? FIRST AIM is to get basics working, then get better
        return favouriteItemDao.getAllFavoriteItems()
    }

    override suspend fun deleteFavoriteItem(uuid: String) {
        favouriteItemDao.deleteFavoriteItem(uuid)
    }

}

interface FavouritesRepository {
    suspend fun insertFavouriteItem(favouriteItem: FavouriteItem)
    suspend fun isFavourite(uuid: String): Boolean
    suspend fun getAllFavoriteItems(): List<FavouriteItem>
    suspend fun deleteFavoriteItem(uuid: String)
}