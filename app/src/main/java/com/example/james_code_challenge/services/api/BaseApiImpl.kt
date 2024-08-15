package com.example.james_code_challenge.services.api

import com.example.james_code_challenge.services.RetrofitHelper

class BaseApiImpl : BaseApi {

    override val procedureApi: ProcedureService = RetrofitHelper.getInstance()
        .create(ProcedureService::class.java)

}

interface BaseApi {

    val procedureApi: ProcedureService

}