package com.cvitirinyu.codingchallenge.starwars.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "missions_table")
data class StarWarsMission (
        @PrimaryKey(autoGenerate = false)
        val id: Int,
        val description: String? = null,
        val title: String? = null,
        val image: String? = null,
        val phone: String? = null,
        val date: String? = null,
        val locationLineOne: String? = null,
        val locationLineTwo: String? = null
)
