package com.thangdn6.weatherapp.di

import com.thangdn6.weatherapp.network.ApiData.CITIES_API_BASE_URL
import com.thangdn6.weatherapp.network.ApiData.WEATHER_API_BASE_URL
import com.thangdn6.weatherapp.network.api.ICitiesService
import com.thangdn6.weatherapp.network.api.IWeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.google.gson.GsonBuilder

import com.google.gson.Gson




@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
     fun provideCitiesSuggestionService(): ICitiesService {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(CITIES_API_BASE_URL)
            .build()
            .create(ICitiesService::class.java)
    }
    @Provides
    @Singleton
    fun provideWeatherService(): IWeatherService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(WEATHER_API_BASE_URL)
            .build()
            .create(IWeatherService::class.java)
    }
}