package com.brq.hellocompose.di

import com.brq.hellocompose.services.provideServices
import com.brq.hellocompose.util.HttpInterceptor
import com.brq.hellocompose.util.NetworkUtils.Companion.BASE_URL
import com.brq.hellocompose.util.provideCache
import com.brq.hellocompose.util.provideOkHttpClient
import com.brq.hellocompose.util.provideRetrofit
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

