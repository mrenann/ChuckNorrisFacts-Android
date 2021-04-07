package com.mrenann.chucknorris_challenge_android.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FactsResult(
    var result: MutableList<Fact>?,
    var total: Int?
)