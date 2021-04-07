package com.mrenann.chucknorris_challenge_android.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Fact(
    val categories: MutableList<String>?,
    @Json(name = "created_at")
    val createdAt: String?,
    @Json(name = "icon_url")
    val iconUrl: String?,
    val id: String?,
    @Json(name = "updated_at")
    val updatedAt: String?,
    val url: String?,
    val value: String?
)