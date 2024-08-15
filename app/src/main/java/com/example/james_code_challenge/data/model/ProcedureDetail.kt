package com.example.recipeextractor.data.model

import com.example.james_code_challenge.data.model.Icon

data class ProcedureDetail(
    val uuid: String,
    val name: String,
    val phases: List<Phase>,
    val icon: Icon,
    val card: Card,
    val specialties: List<String>,
    val labels: List<String>,
    val channel: Channel,
    val overview: List<Int>,
    val devices: List<String>,
    val deepLink: String,
    val viewCount: Int,
    val author: String,
    val organisation: String,
    val doiCode: String,
    val datePublished: String,
    val duration: Int,
    val productIds: Map<String, String>,
    val isPurchasable: Boolean,
    val productInfoUrl: String?,
    val cpdCredits: String?
)

data class Phase(
    val uuid: String,
    val name: String,
    val icon: Icon,
    val deepLink: String,
    val testMode: Boolean,
    val maxUserScore: Any?, // This can be null so marked as nullable
    val viewed: Boolean,
    val learnCompleted: Boolean
)

data class Card(
    val uuid: String,
    val url: String,
    val version: Int
)

data class Channel(
    val banner: Banner
)

data class Banner(
    val uuid: String,
    val url: String,
    val version: Int
)
