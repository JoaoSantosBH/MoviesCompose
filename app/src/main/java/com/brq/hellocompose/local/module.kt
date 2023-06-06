package com.brq.hellocompose.local

import androidx.room.Room
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "movies_compose"
        )
            .build()
    }
    single { get<AppDatabase>().movieDao() }

}

