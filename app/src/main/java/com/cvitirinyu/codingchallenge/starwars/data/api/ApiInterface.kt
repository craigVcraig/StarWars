package com.cvitirinyu.codingchallenge.starwars.data.api

import com.cvitirinyu.codingchallenge.starwars.data.model.StarWarsMission
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("feed.json")
    suspend fun fetchJsonFeed(): Response<List<StarWarsMission>>
}