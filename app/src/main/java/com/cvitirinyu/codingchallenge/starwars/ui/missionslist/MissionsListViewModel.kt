package com.cvitirinyu.codingchallenge.starwars.ui.missionslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cvitirinyu.codingchallenge.starwars.data.database.entities.StarWarsMission
import com.cvitirinyu.codingchallenge.starwars.data.repository.StarWarsMissionsRepository
import kotlinx.coroutines.launch

class MissionsListViewModel(
    private val starWarsMissionsRepository: StarWarsMissionsRepository
) : ViewModel() {

    private val _starWarsMissions = MutableLiveData<List<StarWarsMission>>()
    val starWarsMissions: LiveData<List<StarWarsMission>> = _starWarsMissions

    init {
        viewModelScope.launch {
            val response = starWarsMissionsRepository.getAllMissions()
            _starWarsMissions.value = response
        }
    }
}