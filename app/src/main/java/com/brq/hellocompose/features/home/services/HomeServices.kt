package com.brq.hellocompose.features.home.services

import com.brq.hellocompose.core.util.NetworkUtils.Companion.MOVIES_POPULAR
import com.brq.hellocompose.features.home.data.remote.model.PopularMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeServices {

    @GET(MOVIES_POPULAR)
    suspend fun getPopularMoviesList(
        @Query("language") language: String,
        @Query("page") page: Int,
    ): Response<PopularMoviesResponse>

}


