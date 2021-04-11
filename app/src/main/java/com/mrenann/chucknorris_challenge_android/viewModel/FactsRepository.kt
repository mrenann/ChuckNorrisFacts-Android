package com.mrenann.chucknorris_challenge_android.viewModel

import com.mrenann.chucknorris_challenge_android.network.APIService
import com.mrenann.chucknorris_challenge_android.network.response.ResponseAPI
import java.net.UnknownHostException

class FactsRepository {

    suspend fun getFacts(query: String = ""): ResponseAPI {

        return try {
            val response = APIService.api.search(query)
            if (response.isSuccessful && response.body()?.total!=0) ResponseAPI.Success(response.body())
            else when {
                response.body()?.total == 0 -> ResponseAPI.Error("No Facts matched your search")
                query.length !in 3..119 -> ResponseAPI.Error("Query must be more than 3 chars and less than 120 chars")
                response.code() == 404 -> ResponseAPI.Error("Data not Found")
                else -> ResponseAPI.Error("An error has occurred")
            }
        } catch (exception: UnknownHostException) { ResponseAPI.Error("Could not connect to the server") }
          catch (exception: Exception) { ResponseAPI.Error("An error has occurred") }
    }
}