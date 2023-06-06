package com.brq.hellocompose.util

import android.content.Context
import com.brq.hellocompose.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HttpInterceptor( private val context: Context) :
    Interceptor {

    private val accessToken get(): String? = BuildConfig.API_TOKEN


    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val builder = request.newBuilder()

        val token = accessToken

        builder.header("accept","application/json")
        token?.let { builder.header("Authorization", it) }

        return chain.proceed(builder.build())
    }

}