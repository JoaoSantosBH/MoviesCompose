package br.com.compose.compose_movies_udemy.util

import android.content.Context
import br.com.compose.compose_movies_udemy.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.net.URISyntaxException
import java.util.UUID

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


    //     --url 'https://api.themoviedb.org/3/movie/popular?language=en-US&page=1' \
    //     --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyZDNkZGU1YTZjMzNmNGVhMTZhZTZlZDFlMTQxNzE0ZSIsInN1YiI6IjVhMTVmMTIwMGUwYTI2MzhiYTAwMTc1NyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ks5T5vqcGyk1KM0-vkUbxAyMVAaAxiacfC2Fq6jOFeo' \
    //     --header 'accept: application/json'

}