package com.brq.hellocompose.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.brq.hellocompose.local.entities.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteMovie(movie: MovieEntity)

    @Delete
    fun removeFavoriteMovie(movie: MovieEntity)

    @Query("SELECT * FROM MovieEntity WHERE id = :id" )
    fun getRoom(id: Long): List<MovieEntity>


}