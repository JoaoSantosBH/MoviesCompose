package com.brq.hellocompose

import android.app.Application
import com.brq.hellocompose.core.data.local.di.dbModule
import com.brq.hellocompose.core.di.mainModule
import com.brq.hellocompose.features.details.di.detailModule
import com.brq.hellocompose.features.home.di.homeModule
import com.brq.hellocompose.features.login.di.loginModule
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
            mainModule,
            loginModule,
            homeModule,
            detailModule,
            dbModule
        )
        startKoin {
            androidLogger()
            androidContext(this@CustomApp)
            modules(modules)
        }
    }
}