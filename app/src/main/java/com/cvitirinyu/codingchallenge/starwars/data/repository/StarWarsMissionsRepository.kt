package com.cvitirinyu.codingchallenge.starwars.data.repository

import com.cvitirinyu.codingchallenge.starwars.data.model.StarWarsMission
import retrofit2.Response

interface StarWarsMissionsRepository {
    suspend fun fetchMissions(): Response<List<StarWarsMission>>
}