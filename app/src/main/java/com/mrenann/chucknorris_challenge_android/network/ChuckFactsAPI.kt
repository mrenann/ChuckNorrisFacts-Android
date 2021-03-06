package com.mrenann.chucknorris_challenge_android.network

import com.mrenann.chucknorris_challenge_android.model.FactsResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckFactsAPI {
    @GET("search")
    suspend fun search(
        @Query("query") query: String? = ""
    ): Response<FactsResult>
}