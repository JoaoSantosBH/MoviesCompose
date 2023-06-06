package com.brq.hellocompose.services

import retrofit2.Retrofit

fun provideServices(retrofit: Retrofit): Services {
    return retrofit.create(Services::class.java)
}