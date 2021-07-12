package com.cvitirinyu.codingchallenge.starwars.di

import com.cvitirinyu.codingchallenge.starwars.data.database.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { AppDatabase(context = get()) }
    single { AppDatabase(context = get()).missionsDao() }
}