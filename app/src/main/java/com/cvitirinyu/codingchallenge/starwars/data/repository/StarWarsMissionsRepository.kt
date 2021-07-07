package com.cvitirinyu.codingchallenge.starwars.data.repository

import com.cvitirinyu.codingchallenge.starwars.data.database.entities.StarWarsMission

interface StarWarsMissionsRepository {
    suspend fun fetchMissions():List<StarWarsMission>
}