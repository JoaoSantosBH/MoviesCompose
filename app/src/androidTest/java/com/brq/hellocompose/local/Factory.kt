package com.brq.hellocompose.local

import com.brq.hellocompose.core.data.local.entities.FavoriteMovieEntity

const val  DUMB_MOVIE_ID = 12345

fun createFakeMovie() = FavoriteMovieEntity(
    movieId = 12345
)