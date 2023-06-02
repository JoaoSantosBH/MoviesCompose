package br.com.compose.compose_movies_udemy.presentation

import br.com.compose.compose_movies_udemy.presentation.detail.MovieDetailViewModel
import br.com.compose.compose_movies_udemy.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
}