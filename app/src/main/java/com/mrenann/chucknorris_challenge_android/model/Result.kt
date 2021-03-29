package com.mrenann.chucknorris_challenge_android.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Fact(
    var categories: MutableList<String>?,
    var created_at: String?,
    var icon_url: String?,
    var id: String?,
    var updated_at: String?,
    var url: String?,
    var value: String?
): Parcelable