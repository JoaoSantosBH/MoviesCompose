package com.brq.hellocompose.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.brq.hellocompose.local.dao.MovieDao
import com.brq.hellocompose.local.entities.MovieEntity


@Database(
    entities = [MovieEntity::class, ],
    version = 1,
    exportSchema = false
)

@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao



}