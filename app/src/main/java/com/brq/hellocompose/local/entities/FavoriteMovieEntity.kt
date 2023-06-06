package com.brq.hellocompose.local.entities

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity
data class FavoriteMovieEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = -1,

)
