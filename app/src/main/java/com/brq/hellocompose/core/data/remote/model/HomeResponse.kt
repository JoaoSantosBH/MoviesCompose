package com.brq.hellocompose.core.data.remote.model

import com.brq.hellocompose.core.data.remote.model.MovieResponse.Companion.toDomain
import com.brq.hellocompose.core.domain.MovieModel
import com.brq.hellocompose.core.domain.PopularMoviesModel
import com.google.gson.annotations.SerializedName

data class PopularMoviesResponse(
    @SerializedName("page") val page: Int?,
    @SerializedName("results") val results: List<MovieResponse>,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("total_results") val totalResults: Int?
) {
    companion object {
        fun PopularMoviesResponse.toDomain() = PopularMoviesModel(
            page = this.page,
            results = this.results.toDomain(),
            totalPages=this.totalPages,
            totalResults= this.totalResults
        )

        val fakeResults = listOf(
            MovieResponse(
                adult = false,
                backdropPath = "/nDxJJyA5giRhXx96q1sWbOUjMBI.jpg",
                listOf(28, 35, 14),
                594767,
                originalLanguage = "en",
                originalTitle = "Shazam! Fury of the Gods",
                overview = "Billy Batson and his foster siblings, who transform into superheroes by saying \\\"Shazam!\\\", are forced to get back into action and fight the Daughters of Atlas, who they must stop from using a weapon that could destroy the world.",
                4274.865,
                posterPath = "/2VK4d3mqqTc7LVZLnLPeRiPaJ71.jpg",
                releaseDate = "2023-03-15",
                title = "Shazam! Fury of the Gods",
                false,
                6.9,
                1231
            ),
            MovieResponse(
                adult = false,
                backdropPath = "/gMJngTNfaqCSCqGD4y8lVMZXKDn.jpg",
                listOf(28, 12, 878),
                640146,
                originalLanguage = "en",
                originalTitle = "Ant-Man and the Wasp: Quantumania",
                overview = "Super-Hero partners Scott Lang and Hope van Dyne, along with with Hope's parents Janet van Dyne and Hank Pym, and Scott's daughter Cassie Lang, find themselves exploring the Quantum Realm, interacting with strange new creatures and embarking on an adventure that will push them beyond the limits of what they thought possible.",
                8567.865,
                posterPath = "/ngl2FKBlU4fhbdsrtdom9LVLBXw.jpg",
                releaseDate = "2023-02-15",
                title = "Ant-Man and the Wasp: Quantumania",
                false,
                6.5,
                1886
            )
        )
        val FakeResponse = PopularMoviesResponse(
            page = 1,
            fakeResults,
            1,
            1
        )
    }
}

data class MovieResponse(
    @SerializedName("adult") val adult: Boolean?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("genre_ids") val genreIds: List<Int>?,
    @SerializedName("id") val id: Int?,
    @SerializedName("original_language") val originalLanguage: String?,
    @SerializedName("original_title") val originalTitle: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("popularity") val popularity: Double?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("video") val video: Boolean?,
    @SerializedName("vote_average") val voteAverage: Double?,
    @SerializedName("vote_count") val voteCount: Int?
){
    companion object {
        fun List<MovieResponse>.toDomain() = this.map { it.toDomain() }

        fun MovieResponse.toDomain() = MovieModel(
            adult = this.adult,
            backdropPath= this.backdropPath,
            genreIds= this.genreIds,
            id= this.id,
            originalLanguage= this.originalLanguage,
            originalTitle= this.originalTitle,
            overview= this.overview,
            popularity= this.popularity,
            posterPath= this.posterPath,
            releaseDate= this.releaseDate,
            title= this.title,
            video= this.video,
            voteAverage= this.voteAverage,
            voteCount= this.voteCount
        )
    }
}
