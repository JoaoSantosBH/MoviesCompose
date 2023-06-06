package com.brq.hellocompose.local.entities

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity
data class MovieEntity (
    val adult: Boolean = false,
    val backdropPath: String = "",
    val genreIds: List<Int> = emptyList(),
    @PrimaryKey(autoGenerate = true)
    val id: Int? = -1,
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val releaseDate: String = "",
    val title: String = "",
    val video: Boolean = false,
    val voteAverage: Double = 0.0,
    val voteCount: Int = -1
)
