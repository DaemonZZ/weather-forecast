package com.thangdn6.weatherapp.model.weather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import kotlin.math.roundToInt

data class TempInfo(
    @Expose
    @SerializedName("temp")
    val temp: Double,
    @Expose
    @SerializedName("temp_min")
    val tempMin:Double,
    @Expose
    @SerializedName("temp_max")
    val tempMax:Double
): Serializable {

}
