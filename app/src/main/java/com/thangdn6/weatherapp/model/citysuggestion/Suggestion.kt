package com.thangdn6.weatherapp.model.citysuggestion

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Suggestion(
    @Expose
    @SerializedName("data")
    val data : List<City>
){

    fun getListSuggestion():List<String>{
        val list = mutableListOf<String>()
        for (item in data){
            list.add(item.name)
        }
        return list
    }

}
