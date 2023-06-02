package br.com.compose.compose_movies_udemy.di

import br.com.compose.compose_movies_udemy.services.provideServices
import br.com.compose.compose_movies_udemy.util.HttpInterceptor
import br.com.compose.compose_movies_udemy.util.NetworkUtils.Companion.BASE_URL
import br.com.compose.compose_movies_udemy.util.provideCache
import br.com.compose.compose_movies_udemy.util.provideOkHttpClient
import br.com.compose.compose_movies_udemy.util.provideRetrofit
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val mainModule = module {

    single { HttpInterceptor(get()) }
    single { provideServices(get()) }
    single { provideCache(androidApplication()) }
    single { provideOkHttpClient( get(), androidContext(), get()) }
    single { provideRetrofit(BASE_URL,  get()) }
}

