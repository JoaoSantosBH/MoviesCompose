package com.brq.hellocompose.core.services

import retrofit2.Retrofit

fun provideServices(retrofit: Retrofit): Services {
    return retrofit.create(Services::class.java)
}