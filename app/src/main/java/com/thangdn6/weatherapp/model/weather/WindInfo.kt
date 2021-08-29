package com.thangdn6.weatherapp.model.weather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class WindInfo(
    @Expose
    @SerializedName("speed")
    val speed:Double
): Serializable
