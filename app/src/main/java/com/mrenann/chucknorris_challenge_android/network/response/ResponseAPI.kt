package com.mrenann.chucknorris_challenge_android.network.response

sealed class ResponseAPI {
    class Success(val data: Any?) : ResponseAPI()
    class Error(val message: String) : ResponseAPI()
}