package com.cvitirinyu.codingchallenge.starwars.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "missions_table")
data class StarWarsMission (
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("timestamp")
    val timestamp: String? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("date")
    val date: String? = null,
    @SerializedName("locationline1")
    val locationLineOne: String? = null,
    @SerializedName("locationline2")
    val locationLineTwo: String? = null
)