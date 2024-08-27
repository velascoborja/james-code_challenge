package com.example.james_code_challenge.data.mapper

import com.example.james_code_challenge.data.model.Procedure
import com.example.james_code_challenge.data.model.ProcedureDetail

fun ProcedureDetail.mapToProcedure(): Procedure {
    return Procedure(
        uuid = this.uuid,
        icon = this.icon,
        name = this.name,
        phases = this.phases.map { this.uuid },
        datePublished = this.datePublished,
        duration = this.duration
    )
}