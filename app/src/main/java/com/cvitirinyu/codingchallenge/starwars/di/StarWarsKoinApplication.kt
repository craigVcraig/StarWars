package com.cvitirinyu.codingchallenge.starwars.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StarWarsKoinApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        /* Start Koin */
        startKoin {
            androidContext(this@StarWarsKoinApplication)
            modules(listOf(databaseModule, networkModule, repositoryModule, viewModelModule))
        }
    }
}