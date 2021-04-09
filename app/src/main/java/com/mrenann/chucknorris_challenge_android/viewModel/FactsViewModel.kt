package com.mrenann.chucknorris_challenge_android.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrenann.chucknorris_challenge_android.api.ResponseAPI
import com.mrenann.chucknorris_challenge_android.model.FactsResult
import com.mrenann.chucknorris_challenge_android.model.business.FactsBusiness
import kotlinx.coroutines.launch

class FactsViewModel : ViewModel() {
    val factsList: MutableLiveData<FactsResult> = MutableLiveData()
    val errorList: MutableLiveData<String> = MutableLiveData()

    private val detailed by lazy { FactsBusiness() }

    fun getFacts(query: String = "") {
        viewModelScope.launch {
            when (val response = detailed.getFacts(query)) {
                is ResponseAPI.Success -> factsList.postValue(response.data as FactsResult)
                is ResponseAPI.Error -> errorList.postValue(response.message)
            }
        }
    }

}