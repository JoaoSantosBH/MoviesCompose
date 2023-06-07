package com.brq.hellocompose.core.data.remote.model

import com.brq.hellocompose.core.domain.GenreModel
import com.brq.hellocompose.core.domain.MovieDetailModel
import com.brq.hellocompose.core.domain.ProductionCompanyModel
import com.brq.hellocompose.core.domain.ProductionCountryModel
import com.brq.hellocompose.core.domain.SpokenLanguageModel
import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("adult") val adult: Boolean?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("belongs_to_collection") val belongsToCollection: Any?,
    @SerializedName("budget") val budget: Int?,
    @SerializedName("genres") val genres: List<Genre>?,
    @SerializedName("homepage") val homepage: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("imdb_id") val imdbId: String?,
    @SerializedName("original_language") val originalLanguage: String?,
    @SerializedName("original_title") val originalTitle: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("popularity") val popularity: Double?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("production_companies") val productionCompanies: List<ProductionCompany>?,
    @SerializedName("production_countries") val productionCountries: List<ProductionCountry>?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("revenue") val revenue: Int?,
    @SerializedName("runtime") val runtime: Int?,
    @SerializedName("spoken_languages") val spokenLanguages: List<SpokenLanguage>?,
    @SerializedName("status") val status: String?,
    @SerializedName("tagline") val tagline: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("video") val video: Boolean?,
    @SerializedName("vote_average") val voteAverage: Double?,
    @SerializedName("vote_count") val voteCount: Int?
) {
   companion object {
       fun MovieDetailResponse.toDomain() = MovieDetailModel(
           adult= this.adult ?: false ,
           backdropPath= this.backdropPath ?:"" ,
           belongsCollection =  this.belongsToCollection ?: listOf<Any>() ,
           budget= this.budget ?: -1 ,
           genres = this.genres?.toGenreDomain() ?: listOf(),
           homepage= this.homepage  ?:""  ,
           id= this.id  ?: -1,
           imdbId= this.imdbId  ?:""  ,
           originalLanguage= this.originalLanguage  ?:"" ,
           originalTitle= this.originalTitle  ?:"" ,
           overview= this.overview  ?:"" ,
           popularity= this.popularity ?: 0.0  ,
           posterPath= this.posterPath ?:""  ,
           productionCompanies =  this.productionCompanies?.toProductionCompanyDomain() ?: listOf() ,
           productionCountries = this.productionCountries?.toProductionCountryDomain() ?: listOf(),
           release_date= this.releaseDate ?: "",
           revenue= this.revenue ?: -1,
           runtime= this.runtime ?: -1,
           spokenLanguages=  this.spokenLanguages?.toSpokenLanguageDomain() ?: listOf(),
           status= this.status ?: "" ,
           tagline= this.tagline ?: "",
           title= this.title ?: "",
           video= this.video ?: false,
           voteAverage=  this.voteAverage ?: 0.0,
           voteCount= this.voteCount ?: -1
       )
   }

    fun List<Genre>.toGenreDomain() =
        this.map { it.toDomain() }

    private fun Genre.toDomain() = GenreModel(
        id = this.id ?: -1,
        name = this.name ?: ""
    )

    fun List<ProductionCompany>.toProductionCompanyDomain() = this.map { it.toDomain() }
    private fun ProductionCompany.toDomain() = ProductionCompanyModel(
        id = this.id ?: -1,
        logoPath = this.logoPath ?: "",
        name = this.name ?: "",
        originCountry = this.originCountry ?: ""
    )

    fun List<ProductionCountry>.toProductionCountryDomain() = this.map {it.toDomain() }
    private fun ProductionCountry.toDomain() = ProductionCountryModel(
        iso_3166_1 = this.iso31661 ?: "",
        name = this.name ?: ""
    )

    fun List<SpokenLanguage>.toSpokenLanguageDomain() = this.map { it.toDomain() }
    private fun SpokenLanguage.toDomain() = SpokenLanguageModel(
        englishName = this.englishName ?: "",
        iso_639_1 = this.iso6391 ?: "",
        name = this.name ?: ""

    )
}

data class Genre(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?
)

data class ProductionCompany(
    @SerializedName("id") val id: Int?,
    @SerializedName("logo_path") val logoPath: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("origin_country") val originCountry: String?
)

data class ProductionCountry(
    @SerializedName("iso_3166_1") val iso31661: String?,
    @SerializedName("name") val name: String?
)

data class SpokenLanguage(
    @SerializedName("english_name") val englishName: String?,
    @SerializedName("iso_639_1") val iso6391: String?,
    @SerializedName("name") val name: String?
)

data class Belongs(
    val backdrop_path: String,
    val id: Int,
    val name: String,
    val poster_path: String
)