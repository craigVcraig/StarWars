package com.cvitirinyu.codingchallenge.starwars.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.cvitirinyu.codingchallenge.starwars.data.network.api.ApiInterface
import com.cvitirinyu.codingchallenge.starwars.data.database.dao.MissionsDao
import com.cvitirinyu.codingchallenge.starwars.data.database.entities.StarWarsMission
import com.cvitirinyu.codingchallenge.starwars.data.network.NetworkCallHandler
import com.cvitirinyu.codingchallenge.starwars.data.network.ServiceResult
import com.cvitirinyu.codingchallenge.starwars.utilities.toDbModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StarWarsMissionsRepositoryImpl(
    private val apiInterface: ApiInterface,
    private val missionsDao: MissionsDao,
    private val networkCallHandler: NetworkCallHandler
) : StarWarsMissionsRepository {

    override suspend fun getAllMissions(): List<StarWarsMission> {

        var missionsFromDb = missionsDao.getAllMissions()

        if (missionsFromDb.isNullOrEmpty()) {
            withContext(Dispatchers.IO) {
                val missionResponse = networkCallHandler.makeNetworkCall {
                    apiInterface.fetchJsonFeed()
                }

                when (missionResponse) {
                   is ServiceResult.Success -> {
                       missionResponse.data.map { it.toDbModel() }.let { missions ->
                           missionsDao.insertAll(missions)
                       }
                       missionsFromDb = missionsDao.getAllMissions()
                    }
                    else -> Log.e("Network Error:", "Error getting json feed from server")
                }
            }
        }
        return missionsFromDb
    }

    override fun getMission(missionId: Int): LiveData<StarWarsMission> {
        return missionsDao.getMission(missionId)
    }
}