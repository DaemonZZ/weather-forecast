package com.thangdn6.weatherapp.model.citysuggestion

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class City(
    @Expose
    @SerializedName("id")
    val id:Int,
    @Expose
    @SerializedName("name")
    val name:String
)