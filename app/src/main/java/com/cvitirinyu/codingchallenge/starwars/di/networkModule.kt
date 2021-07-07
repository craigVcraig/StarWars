package com.cvitirinyu.codingchallenge.starwars.di

import com.cvitirinyu.codingchallenge.starwars.data.network.api.ApiInterface
import com.cvitirinyu.codingchallenge.starwars.utilities.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

    val okHttpClient = OkHttpClient.Builder()
        .retryOnConnectionFailure(true)
        .addInterceptor(logger)
        .build()


    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiInterface::class.java)

    /* creates a retrofit singleton */
    single<ApiInterface> { retrofit }

}