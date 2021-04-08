package com.mrenann.chucknorris_challenge_android.api

import com.mrenann.chucknorris_challenge_android.utils.Constants.Api.API_URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object APIService {
    val api: ChuckFactsAPI = getApiClient().create(ChuckFactsAPI::class.java)

    fun getApiClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}