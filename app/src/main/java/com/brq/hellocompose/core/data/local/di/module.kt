package com.brq.hellocompose.core.data.local.di

import androidx.room.Room
import com.brq.hellocompose.core.data.local.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "movies-compose"
        )
            .addMigrations()
            .build()
    }
    single { get<AppDatabase>().movieDao() }

}

