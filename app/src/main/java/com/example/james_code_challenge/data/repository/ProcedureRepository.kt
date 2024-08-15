package com.example.james_code_challenge.data.repository

import com.example.james_code_challenge.data.model.Procedure
import com.example.james_code_challenge.services.api.ProcedureApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.example.james_code_challenge.util.Result
import kotlin.coroutines.cancellation.CancellationException

class ProcedureRepositoryImpl @Inject constructor(
    private val procedureApi: ProcedureApi
) : ProcedureRepository {

    override suspend fun getProcedureList(): Flow<Result<List<Procedure>>> = flow {
        try {
            val response = procedureApi.getProcedureList()
            if (response.isSuccessful) {
                emit(Result.Success(response.body()!!)) // NPEs are caught // TODO
            } else {
                emit(Result.Error(Throwable("API Error: ${response.code()}. ${response.errorBody()}")))
            }
        } catch (e: Exception) {
            if(e is CancellationException) { // TODO find workaround
                throw e
            }
            emit(Result.Error(e))
        }
    }

}

interface ProcedureRepository {
    suspend fun getProcedureList(): Flow<Result<List<Procedure>>>
}