package com.brq.hellocompose.features.details.di

import com.brq.hellocompose.features.details.presentation.MovieDetailViewModel
import com.brq.hellocompose.features.details.services.DetailsServices
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val detailModule = module {
    viewModel { MovieDetailViewModel(get(), get()) }
}

fun provideDetailsServices(retrofit: Retrofit): DetailsServices {
    return retrofit.create(DetailsServices::class.java)
}