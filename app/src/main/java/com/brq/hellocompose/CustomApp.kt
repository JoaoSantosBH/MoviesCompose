package com.brq.hellocompose

import android.app.Application
import com.brq.hellocompose.di.mainModule
import com.brq.hellocompose.local.dbModule
import com.brq.hellocompose.presentation.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class CustomApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        val modules = mutableListOf(
            mainModule, presentationModule, dbModule
        )
        startKoin {
            androidLogger()
            androidContext(this@CustomApp)
            modules(modules)
        }
    }
}