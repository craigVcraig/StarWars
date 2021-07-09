package com.cvitirinyu.codingchallenge.starwars.data.repository

import androidx.lifecycle.LiveData
import com.cvitirinyu.codingchallenge.starwars.data.database.entities.StarWarsMission

interface StarWarsMissionsRepository {
    suspend fun getAllMissions(): List<StarWarsMission>
    fun getMission(missionId: Int): LiveData<StarWarsMission>
}