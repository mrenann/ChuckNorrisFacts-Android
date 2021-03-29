package com.mrenann.chucknorris_challenge_android.model.business

import com.mrenann.chucknorris_challenge_android.api.ResponseAPI
import com.mrenann.chucknorris_challenge_android.model.FactsResult
import com.mrenann.chucknorris_challenge_android.viewModel.FactsRepository

class FactsBusiness {
    private val repository: FactsRepository by lazy{
        FactsRepository()
    }

    suspend fun getFacts(query:String = ""): ResponseAPI{
        val response = repository.getFacts(query)
        return if(response is ResponseAPI.Success) {
            val resposta = response.data as FactsResult
            ResponseAPI.Success(resposta)
        }else response
    }

}