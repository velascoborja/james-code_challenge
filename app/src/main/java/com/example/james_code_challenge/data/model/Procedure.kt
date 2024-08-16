package com.example.james_code_challenge.data.model

import com.google.gson.annotations.SerializedName

data class Procedure(
    val uuid: String,
    val icon: Icon,
    val name: String,
    val phases: List<String>,
    val specialties: List<String>,
    @SerializedName("deep_link") val deepLink: String,
    val author: String,
    val organisation: String,
    @SerializedName("doi_code") val doiCode: String,
    @SerializedName("date_published") val datePublished: String,
    val labels: List<String>,
    @SerializedName("site_slug") val siteSlug: String,
    val duration: Int,
    @SerializedName("is_purchasable") val isPurchasable: Boolean
)

data class Icon(
    val uuid: String,
    val url: String,
    val version: Int
)
