package com.brq.hellocompose.services

import com.brq.hellocompose.remote.model.MovieDetailResponse
import com.brq.hellocompose.remote.model.PopularMoviesResponse
import com.brq.hellocompose.util.NetworkUtils.Companion.MOVIES_POPULAR
import com.brq.hellocompose.util.NetworkUtils.Companion.MOVIE_DETAILS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Services {

    @GET(MOVIES_POPULAR)
    suspend fun getPopularMoviesList(
        @Query("language") language: String,
        @Query("page") page: Int,
    ): Response<PopularMoviesResponse>

    @GET(MOVIE_DETAILS)
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: String,
        @Query("language") language: String
    ): Response<MovieDetailResponse>


}


