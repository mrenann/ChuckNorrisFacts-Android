package com.mrenann.chucknorris_challenge_android.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mrenann.chucknorris_challenge_android.api.APIService
import com.mrenann.chucknorris_challenge_android.api.ChuckFactsAPI
import com.mrenann.chucknorris_challenge_android.model.FactsResult
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FactsViewModel: ViewModel() {
    val factsList: MutableLiveData<FactsResult> = MutableLiveData()
    val errorList: MutableLiveData<String> = MutableLiveData()

    fun getFacts(query:String = "") {
        val retroInstace = APIService.getApiClient().create(ChuckFactsAPI::class.java)
        retroInstace.search(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getFactsRx())

    }

    private fun getFactsRx(): Observer<FactsResult> {

        return object : Observer<FactsResult> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: FactsResult) {
                factsList.postValue(t)
                if(t.result.isNullOrEmpty()) errorList.postValue("Data Not Found")
            }

            override fun onError(e: Throwable) {
                factsList.postValue(null)
                errorList.postValue(e.toString())
            }

            override fun onComplete() {

            }

        }
    }
}