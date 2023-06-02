package br.com.compose.compose_movies_udemy.services

import br.com.compose.compose_movies_udemy.remote.model.PopularMoviesResponse
import br.com.compose.compose_movies_udemy.util.NetworkUtils.Companion.MOVIES_POPULAR
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {

    @GET(MOVIES_POPULAR)
    suspend fun getPopularMoviesList(
        @Query("language") language: String,
        @Query("page") page: Int,
    ): Response<PopularMoviesResponse>
}


