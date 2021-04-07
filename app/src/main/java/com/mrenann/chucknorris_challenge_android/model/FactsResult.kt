package com.mrenann.chucknorris_challenge_android.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FactsResult(
    val result: MutableList<Fact>?,
    val total: Int?
)