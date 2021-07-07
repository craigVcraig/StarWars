package com.cvitirinyu.codingchallenge.starwars.data.repository

import com.cvitirinyu.codingchallenge.starwars.data.network.api.ApiInterface
import com.cvitirinyu.codingchallenge.starwars.data.database.dao.MissionsDao
import com.cvitirinyu.codingchallenge.starwars.data.database.entities.StarWarsMission
import com.cvitirinyu.codingchallenge.starwars.data.network.model.StarWarsMissionResponse
import com.cvitirinyu.codingchallenge.starwars.utilities.toDbModel
import com.cvitirinyu.codingchallenge.starwars.utilities.toRequiredDateFormat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StarWarsMissionsRepositoryImpl(
    private val apiInterface: ApiInterface,
    private val missionsDao: MissionsDao
) : StarWarsMissionsRepository{

    override suspend fun fetchMissions(): List<StarWarsMission> {

        var missionsFromDb = missionsDao.getAllMissions()

        if (missionsFromDb.isNullOrEmpty()){
            withContext(Dispatchers.IO){
                val missionsResponse = apiInterface.fetchJsonFeed()

                if (missionsResponse.isSuccessful && missionsResponse.body() != null){

                    missionsResponse.body()?.map { it.toDbModel() }?.let { missions ->
                        missionsDao.insertAll(missions)
                    }

                    missionsFromDb = missionsDao.getAllMissions()
                }
            }
        }
        return missionsFromDb
    }
}