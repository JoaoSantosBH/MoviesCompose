package br.com.compose.compose_movies_udemy.remote.model

import br.com.compose.compose_movies_udemy.domain.MovieModel
import br.com.compose.compose_movies_udemy.domain.PopularMoviesModel
import br.com.compose.compose_movies_udemy.remote.model.MovieResponse.Companion.toDomain
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
            results = this.results.toDomain() ?: listOf(),
            totalPages=this.totalPages,
            totalResults= this.totalResults
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
