package com.brq.hellocompose.features.details.services

import com.brq.hellocompose.core.util.NetworkUtils.Companion.MOVIE_DETAILS
import com.brq.hellocompose.features.details.data.remote.model.MovieDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailsServices {

    @GET(MOVIE_DETAILS)
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: String,
        @Query("language") language: String
    ): Response<MovieDetailResponse>

}


