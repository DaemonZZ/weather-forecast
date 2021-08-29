package com.thangdn6.weatherapp.model.weather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import kotlin.math.roundToInt

data class Weather(
    @Expose
    @SerializedName("weather")
    val status: List<Condition>,
    @Expose
    @SerializedName("main")
    val temp: TempInfo,
    @Expose
    @SerializedName("wind")
    val wind: WindInfo,
    @Expose
    @SerializedName("visibility")
    val visibility:Int,
    @Expose
    @SerializedName("sys")
    val country: Country,
    @Expose
    @SerializedName("name")
    val city:String

):Serializable {
    fun getLocation() = "$city, ${country.name}"
    fun getIcon() = status[0].getIconLink()
    fun getDescription() = status[0].description
    fun getVis() = "${visibility/1000} km"
    fun getWindSpeed() = "${wind.speed} km/h"
    fun getTempplate():String{
        val celsius = temp.temp - 273.15
        return celsius.roundToInt().toString()
    }
}
