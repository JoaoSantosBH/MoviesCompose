package com.brq.hellocompose.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteMovieEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "movieId")
    val movieId: Int?,

)

fun List<FavoriteMovieEntity>.toDomain() = this.map {
    it.movieId
}

fun Int.toLocal() = FavoriteMovieEntity(null,this)
