package com.brq.hellocompose.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.brq.hellocompose.local.entities.FavoriteMovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteMovie(movie: FavoriteMovieEntity)

    @Delete
    fun removeFavoriteMovie(movie: FavoriteMovieEntity)

    @Query("SELECT * FROM FavoriteMovieEntity WHERE id = :id")
    fun getFavoriteMovie(id: Int): List<FavoriteMovieEntity>


}