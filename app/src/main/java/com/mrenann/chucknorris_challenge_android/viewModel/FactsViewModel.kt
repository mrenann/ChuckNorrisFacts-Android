package com.mrenann.chucknorris_challenge_android.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrenann.chucknorris_challenge_android.network.response.ResponseAPI
import com.mrenann.chucknorris_challenge_android.model.FactsResult
import com.mrenann.chucknorris_challenge_android.model.business.FactsBusiness
import kotlinx.coroutines.launch

class FactsViewModel(private val business: FactsBusiness) : ViewModel() {
    val factsList: MutableLiveData<FactsResult> = MutableLiveData()
    val errorList: MutableLiveData<String> = MutableLiveData()

    fun getFacts(query: String = "") {
        viewModelScope.launch {
            when (val response = business.getFacts(query)) {
                is ResponseAPI.Success -> factsList.postValue(response.data as FactsResult)
                is ResponseAPI.Error -> errorList.postValue(response.message)
            }
        }
    }

}