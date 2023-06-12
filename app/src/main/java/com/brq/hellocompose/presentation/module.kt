package com.brq.hellocompose.presentation

import com.brq.hellocompose.presentation.detail.MovieDetailViewModel
import com.brq.hellocompose.presentation.home.HomeViewModel
import com.brq.hellocompose.presentation.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { MovieDetailViewModel(get(), get()) }
    viewModel { LoginViewModel() }
}