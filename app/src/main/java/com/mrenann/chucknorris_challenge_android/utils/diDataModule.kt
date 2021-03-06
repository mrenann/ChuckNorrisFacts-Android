package com.mrenann.chucknorris_challenge_android.utils

import com.mrenann.chucknorris_challenge_android.model.business.FactsBusiness
import com.mrenann.chucknorris_challenge_android.viewModel.FactsRepository
import com.mrenann.chucknorris_challenge_android.viewModel.FactsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    factory {
        FactsBusiness(FactsRepository())
    }

    viewModel {
        FactsViewModel(
            business = get()
        )
    }
}
