package com.cvitirinyu.codingchallenge.starwars.di

import com.cvitirinyu.codingchallenge.starwars.data.repository.StarWarsMissionsRepository
import com.cvitirinyu.codingchallenge.starwars.data.repository.StarWarsMissionsRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<StarWarsMissionsRepository> {
        StarWarsMissionsRepositoryImpl(
            apiInterface = get(),
            missionsDao = get(),
            networkCallHandler = get()
        )
    }
}