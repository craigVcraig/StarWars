package com.cvitirinyu.codingchallenge.starwars.ui.missiondetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cvitirinyu.codingchallenge.starwars.data.database.entities.StarWarsMission
import com.cvitirinyu.codingchallenge.starwars.data.repository.StarWarsMissionsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MissionDetailsViewModel(
    private val starWarsMissionsRepository: StarWarsMissionsRepository
) : ViewModel(){

    fun getMission(missionId: Int): LiveData<StarWarsMission> {
        return starWarsMissionsRepository.getMission(missionId)
    }

}