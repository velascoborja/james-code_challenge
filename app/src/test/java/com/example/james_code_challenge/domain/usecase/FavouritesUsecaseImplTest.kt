package com.example.james_code_challenge.domain.usecase

import com.example.james_code_challenge.data.model.FavouriteItem
import com.example.james_code_challenge.data.repository.FavouritesRepository
import com.example.james_code_challenge.mock.MockData
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkObject
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.any
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class FavouritesUsecaseImplTest {

    private val repository = mock(FavouritesRepository::class.java)
//    private val useCase = mock(FavouritesUsecase::class.java)
    private val useCase: FavouritesUsecase = mock(FavouritesUsecase::class.java)
//    private lateinit var useCase: FavouritesUsecase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

//        useCase = FavouritesUsecaseImpl(repository)
    }

    @Test
    fun `toggleFavourite should insert item when not favourite`() = runTest {
        // given
        val favouriteItem = FavouriteItem(uuid = "123", MockData.procedureMock)
        coEvery { repository.isFavourite(favouriteItem.uuid) } returns false

        // when
        useCase.toggleFavourite(favouriteItem)

        // then
        verify(repository).insertFavouriteItem(favouriteItem)
        verify(repository, never()).deleteFavoriteItem(any())
    }

//    @Test
//    fun `toggleFavourite should delete item when favourite`() = runTest {
//        val favouriteItem = FavouriteItem(uuid = "123")
//        whenever(repository.isFavourite(any())).thenReturn(true)
//
//        useCase.toggleFavourite(favouriteItem)
//
//        verify(repository).deleteFavoriteItem(favouriteItem.uuid)
//        verify(repository, never()).insertFavouriteItem(any())
//    }
//
//    @Test
//    fun `isFavourite should return repository result`() = runTest {
//        val uuid = "123"
//        val isFavourite = true
//        whenever(repository.isFavourite(uuid)).thenReturn(isFavourite)
//
//        val result = useCase.isFavourite(uuid)
//
//        assertEquals(isFavourite, result)
//    }
//
//    @Test
//    fun `getAllFavoriteItems should return repository result`() = runTest {
//        val favouriteItems = listOf(FavouriteItem(uuid = "123"))
//        whenever(repository.getAllFavoriteItems()).thenReturn(favouriteItems)
//
//        val result = useCase.getAllFavoriteItems().first()
//
//        assertEquals(favouriteItems, result)
//    }

}