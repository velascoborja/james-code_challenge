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
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val procedureUsecase: ProcedureUsecase,
    private val favouritesUsecase: FavouritesUsecase
) : ViewModel() {

    private val _proceduresState = MutableStateFlow(ProceduresState())
    val proceduresState: StateFlow<ProceduresState> = _proceduresState.asStateFlow()

    fun fetchProceduresAndFavourites() {
        viewModelScope.launch {
            _proceduresState.emit(_proceduresState.value.copy(isLoading = true))

            val procedures = procedureUsecase.getProcedureList()
            val favourites = favouritesUsecase.getAllFavoriteItems()

            combine(procedures, favourites) { proceduresCall, favouriteItems ->
                when(proceduresCall) { // Perhaps not the best idea to rely on the success of just API call here, but for sake of this technical
                    is Result.Success -> onProcedureListSuccess(proceduresCall.data, favouriteItems)
                    is Result.Error -> onProcedureListFailure(proceduresCall.exception)
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun onProcedureListSuccess(procedureList: List<Procedure>, favouriteItems: List<FavouriteItem>) {
        viewModelScope.launch {
            _proceduresState.emit(
                _proceduresState.value.copy(
                    isLoading = false,
                    items = procedureList,
                    favouriteItems = favouriteItems.map { it.procedure }
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

            // Emit event with latest list of favourites for use in DB
            viewModelScope.launch {
                favouritesUsecase.getAllFavoriteItems().collect { fav ->
                    _proceduresState.emit(
                        _proceduresState.value.copy(
                            favouriteItems = fav.map { it.procedure }
                        )
                    )
                }
            }
        }
    }

    fun fetchFavouriteList() {
        viewModelScope.launch {
            favouritesUsecase.getAllFavoriteItems().collect { favouritesList ->
                val favouriteProcedures = favouritesList.map { it.procedure }
                _proceduresState.emit(
                    _proceduresState.value.copy(
                        items = favouriteProcedures,
                        favouriteItems = favouriteProcedures,
                    )
                )
            }
        }
    }

    fun isFavourite(currentUuid: String): Boolean {
        val currentFavourites: List<String> = proceduresState.value.favouriteItems.map { it.uuid }
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
        val favouriteItems: List<Procedure> = emptyList(),
        var selectedProcedureDetail: ProcedureDetail? = null
    )

}