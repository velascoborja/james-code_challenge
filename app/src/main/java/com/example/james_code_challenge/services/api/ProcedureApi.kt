package com.example.james_code_challenge.services.api

import com.example.james_code_challenge.data.model.Procedure
import com.example.james_code_challenge.data.model.ProcedureDetail
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

class ProcedureApi @Inject constructor(private val retrofit: Retrofit): ProcedureService {

    override suspend fun getProcedureList(): Response<List<Procedure>> {
        return retrofit.create(ProcedureService::class.java).getProcedureList()
    }

    override suspend fun getProcedureDetail(procedureId: String): Response<ProcedureDetail> {
        return retrofit.create(ProcedureService::class.java).getProcedureDetail(procedureId)
    }

}

interface ProcedureService {

    @GET("procedures")
    suspend fun getProcedureList() : Response<List<Procedure>>

    @GET("procedures/{procedureId}")
    suspend fun getProcedureDetail(
        @Path("procedureId") procedureId: String
    ): Response<ProcedureDetail>

}