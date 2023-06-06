package com.brq.hellocompose.local

import androidx.room.Room
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

