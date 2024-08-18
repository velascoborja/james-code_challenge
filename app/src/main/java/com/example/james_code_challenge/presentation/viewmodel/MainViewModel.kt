package com.example.james_code_challenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.james_code_challenge.data.model.Procedure
import com.example.james_code_challenge.data.model.ProcedureDetail
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
    private val procedureUsecase: ProcedureUsecase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProceduresListState())
    val uiState: StateFlow<ProceduresListState> = _uiState.asStateFlow()

    init {
        fetchProcedureList()
    }

    private fun fetchProcedureList() {
        viewModelScope.launch {
            _uiState.emit(_uiState.value.copy(isLoading = true))

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
            _uiState.emit(_uiState.value.copy(isLoading = false, items = procedureList))
        }
    }

    private fun onProcedureListFailure(exception: Throwable) {
        viewModelScope.launch {
            _uiState.emit(_uiState.value.copy(isLoading = false, error = exception.toString()))
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
            _uiState.emit(
                _uiState.value.copy(
                    isLoading = false,
                    selectedProcedureDetail = procedureDetail
                )
            )
        }
    }

    private fun onProcedureDetailFailure() {
        viewModelScope.launch {
            _uiState.emit(_uiState.value.copy(isLoading = false, selectedProcedureDetail = null))
        }
    }

    data class ProceduresListState(
        val isLoading: Boolean = false,
        val items: List<Procedure> = emptyList(),
        val error: String? = null, // Would use an ErrorType on actual app
        val favouriteItems: List<Procedure> = emptyList(),
        var selectedProcedureDetail: ProcedureDetail? = null
    )

}