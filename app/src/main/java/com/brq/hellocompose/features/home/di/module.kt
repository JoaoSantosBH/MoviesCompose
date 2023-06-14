package com.brq.hellocompose.features.home.di

import com.brq.hellocompose.features.home.presentation.HomeViewModel
import com.brq.hellocompose.features.home.services.HomeServices
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val homeModule = module {
    viewModel { HomeViewModel(get(), get()) }
}

fun provideHomeServices(retrofit: Retrofit): HomeServices {
    return retrofit.create(HomeServices::class.java)
}