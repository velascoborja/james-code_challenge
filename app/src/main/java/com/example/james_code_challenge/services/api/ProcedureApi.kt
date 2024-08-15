package com.example.james_code_challenge.services.api

import com.example.james_code_challenge.data.model.Procedure
import com.example.recipeextractor.data.model.ProcedureDetail
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Inject

class ProcedureApi @Inject constructor(): ProcedureService {

    private val baseApi = BaseApiImpl()

    override suspend fun getProcedureList(): Response<List<Procedure>> {
        return baseApi.procedureApi.getProcedureList()
    }

    override suspend fun getProcedureDetails(): Response<ProcedureDetail> {
        return baseApi.procedureApi.getProcedureDetails()
    }

}

interface ProcedureService {
    @GET("procedures")
    suspend fun getProcedureList() : Response<List<Procedure>>

//    @GET(procedures/$PROCEDURE_ID)
    suspend fun getProcedureDetails() : Response<ProcedureDetail>
}