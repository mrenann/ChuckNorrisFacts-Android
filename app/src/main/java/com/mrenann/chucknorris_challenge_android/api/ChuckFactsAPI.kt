package com.mrenann.chucknorris_challenge_android.api

import com.mrenann.chucknorris_challenge_android.model.FactsResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ChuckFactsAPI {
    @GET("search?query={query}")
    suspend fun search(
        @Path("query") query: String
    ): Response<FactsResult>
}