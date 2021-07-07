package com.cvitirinyu.codingchallenge.starwars.data.api

import com.cvitirinyu.codingchallenge.starwars.data.model.StarWarsMissionResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("feed.json")
    suspend fun fetchJsonFeed(): Response<List<StarWarsMissionResponse>>
}