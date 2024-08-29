package com.example.james_code_challenge.data.model

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Procedure(
    val uuid: String,
    @Embedded val icon: Icon,
    val name: String,
    @Embedded val phases: List<String>,
    @SerializedName("date_published") val datePublished: String,
    val duration: Int
)

data class Icon(
    @SerializedName("uuid") val iconUuid: String,
    @SerializedName("url") val iconUrl: String,
    val version: Int
)
