package com.example.james_code_challenge.data.model

import com.google.gson.annotations.SerializedName

data class ProcedureDetail(
    val uuid: String,
    val name: String,
    val phases: List<Phase>,
    val icon: Icon,
    val card: Card,
    @SerializedName("date_published") val datePublished: String,
    val duration: Int,
)

data class Phase(
    val uuid: String,
    val name: String,
    val icon: Icon
)

data class Card(
    val uuid: String,
    val url: String,
    val version: Int
)
