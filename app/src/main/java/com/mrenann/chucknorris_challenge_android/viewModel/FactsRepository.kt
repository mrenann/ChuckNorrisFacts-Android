package com.mrenann.chucknorris_challenge_android.viewModel

import com.mrenann.chucknorris_challenge_android.api.APIService
import com.mrenann.chucknorris_challenge_android.api.ResponseAPI
import java.lang.Exception

class FactsRepository {

    suspend fun getFacts(query:String = ""): ResponseAPI {
        return try{
            val response = APIService.api.search(query)
            if (response.isSuccessful) {
                ResponseAPI.Success(response.body())
            } else {
                if (response.code() == 404) {
                    ResponseAPI.Error("Data not Found")
                } else {
                    ResponseAPI.Error("Error loading data")
                }
            }
        }catch (exception:Exception){
            ResponseAPI.Error("Error loading data")
        }
    }
}