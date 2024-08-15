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
    @SerializedName("doi_code") val doiCode: String, // Consider using nullable type (String?) if code might be missing
    @SerializedName("date_published") val datePublished: String, // Consider using a Date or LocalDate type for better handling
    val labels: List<String>, // Consider using an empty list by default if labels might be missing (emptyList<String>())
    @SerializedName("site_slug") val siteSlug: String,
    val duration: Int,
    @SerializedName("is_purchasable") val isPurchasable: Boolean
)

data class Icon(
    val uuid: String,
    val url: String,
    val version: Int
)
