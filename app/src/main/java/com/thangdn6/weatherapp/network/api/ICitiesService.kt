package com.thangdn6.weatherapp.network.api

import com.thangdn6.weatherapp.model.citysuggestion.Suggestion
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query
interface ICitiesService {
    @GET("api.php")
    fun suggest(@Query("namePrefix") namePrefix:String
    ) : Observable<Suggestion>


}