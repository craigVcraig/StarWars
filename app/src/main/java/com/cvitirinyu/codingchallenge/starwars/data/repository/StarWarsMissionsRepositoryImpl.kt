package com.cvitirinyu.codingchallenge.starwars.data.repository

import com.cvitirinyu.codingchallenge.starwars.data.api.ApiInterface
import com.cvitirinyu.codingchallenge.starwars.data.database.dao.MissionsDao
import com.cvitirinyu.codingchallenge.starwars.data.model.StarWarsMission
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class StarWarsMissionsRepositoryImpl(
    private val apiInterface: ApiInterface,
    private val missionsDao: MissionsDao
) : StarWarsMissionsRepository{
    override suspend fun fetchMissions(): Response<List<StarWarsMission>> {
        return withContext(Dispatchers.IO){
            apiInterface.fetchJsonFeed()
        }
    }
}