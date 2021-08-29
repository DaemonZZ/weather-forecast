package com.thangdn6.weatherapp.network.api

import com.thangdn6.weatherapp.model.weather.Weather
import com.thangdn6.weatherapp.network.ApiData
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IWeatherService {
    @GET("weather")
    fun getWeather(
        @Query("id") city : Int = 1583992,
        @Query("appid") id:String = ApiData.API_KEY,
        @Query("lang") lang:String = "vi"
    ): Observable<Weather>
}