package com.cvitirinyu.codingchallenge.starwars.data.network.model

import com.google.gson.annotations.SerializedName

data class StarWarsMissionResponse (
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