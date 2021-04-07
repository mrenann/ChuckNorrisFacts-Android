package com.mrenann.chucknorris_challenge_android.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Fact(
    var categories: MutableList<String>?,
    @Json(name = "created_at")
    var createdAt: String?,
    @Json(name = "icon_url")
    var iconUrl: String?,
    var id: String?,
    @Json(name = "updated_at")
    var updatedAt: String?,
    var url: String?,
    var value: String?
)