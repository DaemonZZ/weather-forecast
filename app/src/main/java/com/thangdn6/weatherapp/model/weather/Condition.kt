package com.thangdn6.weatherapp.model.weather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Condition(
    @Expose
    @SerializedName("main")
    val status: String,

    @Expose
    @SerializedName("description")
    val description:String,

    @Expose
    @SerializedName("icon")
    val icon:String
): Serializable {
    fun getIconLink() = "http://openweathermap.org/img/wn/$icon@2x.png"


}
