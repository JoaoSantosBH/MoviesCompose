package com.brq.hellocompose.core.di

import com.brq.hellocompose.core.util.HttpInterceptor
import com.brq.hellocompose.core.util.NetworkUtils.Companion.BASE_URL
import com.brq.hellocompose.core.util.provideCache
import com.brq.hellocompose.core.util.provideOkHttpClient
import com.brq.hellocompose.core.util.provideRetrofit
import com.brq.hellocompose.features.details.di.provideDetailsServices
import com.brq.hellocompose.features.home.di.provideHomeServices
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val mainModule = module {

    single { HttpInterceptor(get()) }
    single { provideHomeServices(get()) }
    single { provideDetailsServices(get()) }
    single { provideCache(androidApplication()) }
    single { provideOkHttpClient( get(), androidContext(), get()) }
    single { provideRetrofit(BASE_URL,  get()) }
}

