package com.mrenann.chucknorris_challenge_android.utils

import com.mrenann.chucknorris_challenge_android.viewModel.FactsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel {
        FactsViewModel()
    }
}
