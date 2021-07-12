package com.cvitirinyu.codingchallenge.starwars.ui.missiondetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cvitirinyu.codingchallenge.starwars.data.database.entities.StarWarsMission
import com.cvitirinyu.codingchallenge.starwars.data.repository.StarWarsMissionsRepository

class MissionDetailsViewModel(
    private val starWarsMissionsRepository: StarWarsMissionsRepository
) : ViewModel() {

    fun getMission(missionId: Int): LiveData<StarWarsMission> {
        return starWarsMissionsRepository.getMission(missionId)
    }

}