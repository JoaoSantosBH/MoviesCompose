package com.brq.hellocompose.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.brq.hellocompose.features.details.data.local.dao.MovieDao
import com.brq.hellocompose.features.details.data.local.entities.FavoriteMovieEntity


@Database(
    entities = [FavoriteMovieEntity::class, ],
    version = 2,
    exportSchema = false
)

@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao



}