package com.mrenann.chucknorris_challenge_android.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrenann.chucknorris_challenge_android.api.ResponseAPI
import com.mrenann.chucknorris_challenge_android.model.business.FactsBusiness
import com.mrenann.chucknorris_challenge_android.model.FactsResult
import kotlinx.coroutines.launch

class FactsViewModel: ViewModel() {

    val sucess: MutableLiveData<FactsResult> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()
    private val detailed by lazy {
        FactsBusiness()
    }

    fun getFacts(query:String = "") {
        viewModelScope.launch {
            when(val response = detailed.getFacts(query) ) {
                is ResponseAPI.Success -> {
                    sucess.postValue(response.data as FactsResult)

                }
                is ResponseAPI.Error -> {
                    error.postValue(response.message)
                }
            }
        }
    }

}