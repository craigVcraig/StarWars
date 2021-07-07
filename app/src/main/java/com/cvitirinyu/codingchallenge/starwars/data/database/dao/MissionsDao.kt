package com.cvitirinyu.codingchallenge.starwars.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cvitirinyu.codingchallenge.starwars.data.model.StarWarsMission

@Dao
interface MissionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(missions: List<StarWarsMission>)

//    @Query("SELECT * FROM missions_table")
//    suspend fun getAllMissions(): List<StarWarsMission>

  // TODO: Use Flow
}