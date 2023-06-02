package br.com.compose.compose_movies_udemy.util

import android.app.Application
import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


typealias Builder = OkHttpClient.Builder

fun provideRetrofit(
    baseUrl: String,
    client: OkHttpClient.Builder
): Retrofit {
    return Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client.build()).build()
}



fun provideOkHttpClient(
    cache: Cache,
    context: Context,
    interceptor: HttpInterceptor


): OkHttpClient.Builder {
    return OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .cache(cache)

}

fun provideCache(application: Application): Cache {
    val cacheSize = 10 * 1024 * 1024
    return Cache(application.cacheDir, cacheSize.toLong())
}