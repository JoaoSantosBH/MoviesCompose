package br.com.compose.compose_movies_udemy.services

import retrofit2.Retrofit

fun provideServices(retrofit: Retrofit): Services {
    return retrofit.create(Services::class.java)
}