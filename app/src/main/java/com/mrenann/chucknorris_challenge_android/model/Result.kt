package com.mrenann.chucknorris_challenge_android.model

import com.google.gson.annotations.SerializedName

data class Fact(
    var categories: MutableList<String>?,
    @SerializedName("created_at")
    var createdAt: String?,
    @SerializedName("icon_url")
    var iconUrl: String?,
    var id: String?,
    @SerializedName("updated_at")
    var updatedAt: String?,
    var url: String?,
    var value: String?
)