package com.mrenann.chucknorris_challenge_android.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FactsResult(
    var result: MutableList<Fact>?,
    var total: Int?
):Parcelable