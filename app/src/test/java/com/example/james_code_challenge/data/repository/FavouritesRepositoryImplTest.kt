package com.example.james_code_challenge.data.repository

import com.example.james_code_challenge.data.database.dao.FavouriteItemDao
import com.example.james_code_challenge.data.model.FavouriteItem
import com.example.james_code_challenge.mock.MockData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FavouritesRepositoryImplTest {
// TODO 1 more test & Fix (test being a end to end? Main point of it is to ensure only 1 call happens at a time..)
    // TODO CALL VERIFY TO ENSURE THAT CALLS ARE MADE AS WELL AS LAMBDAS?
    private val favouriteItemDaoMock = mockk<FavouriteItemDao>(relaxed = true)

    private lateinit var favouritesRepository: FavouritesRepository

    @Before
    fun setUp() {
        favouritesRepository = FavouritesRepositoryImpl(favouriteItemDaoMock)
    }

    @Test
    fun `WHEN insertFavouriteItem() THEN Dao insertFavoriteItem()`() = runBlocking {
        // given
        val favouriteItem = FavouriteItem(uuid = "123", procedure = MockData.procedureMock)

        // when
        favouritesRepository.insertFavouriteItem(favouriteItem)

        // then
        coVerify { favouriteItemDaoMock.insertFavoriteItem(favouriteItem) }
    }

    @Test
    fun `getAllFavoriteItems should return items from favouriteItemDao`() = runBlocking {
        // given
        val expectedItems = listOf(FavouriteItem(uuid = "123", procedure = MockData.procedureMock))
        coEvery { favouriteItemDaoMock.getAllFavoriteItems() } returns expectedItems

        // when
        val actualItems = favouritesRepository.getAllFavoriteItems()

        // then
        assert(actualItems == expectedItems)
    }

    @Test
    fun `deleteFavoriteItem should call favouriteItemDao deleteFavoriteItem`() = runBlocking {
        // given
        val uuid = "123"

        // when
        favouritesRepository.deleteFavoriteItem(uuid)

        // then
        coVerify { favouriteItemDaoMock.deleteFavoriteItem(uuid) }
    }

}