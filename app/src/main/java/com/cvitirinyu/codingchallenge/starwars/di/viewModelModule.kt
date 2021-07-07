package com.cvitirinyu.codingchallenge.starwars.di

import com.cvitirinyu.codingchallenge.starwars.ui.missionslist.MissionsListViewModel
import org.koin.dsl.module

val viewModelModule = module {
   single { MissionsListViewModel(starWarsMissionsRepository = get()) }
}