package br.com.compose.compose_movies_udemy

import android.app.Application
import br.com.compose.compose_movies_udemy.di.mainModule
import br.com.compose.compose_movies_udemy.presentation.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.Module

class CustomApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        val modules = mutableListOf(
            mainModule,presentationModule
        )
        startKoin {
            androidLogger()
            androidContext(this@CustomApp)
            modules(modules)
        }
    }
}