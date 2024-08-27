package com.example.james_code_challenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.james_code_challenge.data.model.FavouriteItem
import com.example.james_code_challenge.data.model.Procedure
import com.example.james_code_challenge.data.model.ProcedureDetail
import com.example.james_code_challenge.domain.usecase.FavouritesUsecase
import com.example.james_code_challenge.domain.usecase.ProcedureUsecase
import com.example.james_code_challenge.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val procedureUsecase: ProcedureUsecase,
    private val favouritesUsecase: FavouritesUsecase
) : ViewModel() {

    private val _proceduresState = MutableStateFlow(ProceduresState())
    val proceduresState: StateFlow<ProceduresState> = _proceduresState.asStateFlow()

    private val _favouritesState = MutableStateFlow(FavouritesState())
    val favouritesState: StateFlow<FavouritesState> = _favouritesState.asStateFlow()

    fun fetchProcedureList() {
        viewModelScope.launch {
            _proceduresState.emit(_proceduresState.value.copy(isLoading = true))

            fetchFavouriteList()
            procedureUsecase.getProcedureList().collect {
                when (it) {
                    is Result.Success -> onProcedureListSuccess(it.data)
                    is Result.Error -> onProcedureListFailure(it.exception)
                }
            }
        }
    }

    private fun onProcedureListSuccess(procedureList: List<Procedure>) {
        viewModelScope.launch {
            _proceduresState.emit(
                _proceduresState.value.copy(
                    isLoading = false,
                    items = procedureList
                )
            )
        }
    }

    private fun onProcedureListFailure(exception: Throwable) {
        viewModelScope.launch {
            _proceduresState.emit(
                _proceduresState.value.copy(
                    isLoading = false,
                    error = exception.toString()
                )
            )
        }
    }

    fun fetchProcedureDetail(procedureId: String) {
        viewModelScope.launch {
            procedureUsecase.getProcedureDetail(procedureId).collect {
                when (it) {
                    is Result.Success -> onProcedureDetailSuccess(it.data)
                    is Result.Error -> onProcedureDetailFailure()
                }
            }
        }
    }

    private fun onProcedureDetailSuccess(procedureDetail: ProcedureDetail) {
        viewModelScope.launch {
            _proceduresState.emit(
                _proceduresState.value.copy(
                    isLoading = false,
                    selectedProcedureDetail = procedureDetail
                )
            )
            _favouritesState.emit(
                _favouritesState.value.copy(
                    selectedProcedureDetail = procedureDetail
                )
            )
        }
    }

    private fun onProcedureDetailFailure() {
        viewModelScope.launch {
            _proceduresState.emit(
                _proceduresState.value.copy(
                    isLoading = false,
                    selectedProcedureDetail = null
                )
            )
        }
    }

    fun toggleFavouriteItem(favouriteItem: FavouriteItem) {
        viewModelScope.launch {
            favouritesUsecase.toggleFavourite(favouriteItem)
        }
    }

    fun fetchFavouriteList() {
        viewModelScope.launch {
            favouritesUsecase.getAllFavoriteItems().collect { favouritesList ->
                val procedures = favouritesList.map { it.procedure }
                _favouritesState.emit(
                    _favouritesState.value.copy(
                        items = procedures
                    )
                )
                _proceduresState.emit( // Pass copy of current favourites to modify state of favourite button
                    _proceduresState.value.copy(
                        favouriteItems = procedures
                    )
                )
            }
        }
    }

    fun isFavourite(currentUuid: String): Boolean {
        val currentFavourites: List<String> = proceduresState.value.favouriteItems.map { it.uuid }
        println("JIMMY CURRENT FAVOURITES -> $currentFavourites")
        return currentFavourites.contains(currentUuid)
    }

    data class ProceduresState(
        val isLoading: Boolean = false,
        val items: List<Procedure> = emptyList(),
        val error: String? = null, // Would use an ErrorType on actual app
        val favouriteItems: List<Procedure> = emptyList(),
        var selectedProcedureDetail: ProcedureDetail? = null
    )

    data class FavouritesState(
        val items: List<Procedure> = emptyList(),
        var selectedProcedureDetail: ProcedureDetail? = null
    )

}