package com.mrenann.chucknorris_challenge_android.api

import com.mrenann.chucknorris_challenge_android.model.FactsResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckFactsAPI {
    @GET("search")
    fun search(
        @Query("query") query: String? = ""
    ): Observable<FactsResult>
}