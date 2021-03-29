package com.mrenann.chucknorris_challenge_android.api

import com.mrenann.chucknorris_challenge_android.utils.Constants.Api.API_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object APIService {
    val api: ChuckFactsAPI = getApiClient().create(ChuckFactsAPI::class.java)

    fun getApiClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}