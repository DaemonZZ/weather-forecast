package com.thangdn6.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import com.thangdn6.weatherapp.R
import com.thangdn6.weatherapp.databinding.ActivityMainBinding
import com.thangdn6.weatherapp.extension.UIHelper
import com.thangdn6.weatherapp.model.citysuggestion.City
import com.thangdn6.weatherapp.network.ApiData.DEFAULT_ID
import com.thangdn6.weatherapp.network.api.ICitiesService
import com.thangdn6.weatherapp.network.api.IWeatherService
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "ThangDN6 - MainActivity"
    }

    private lateinit var binding: ActivityMainBinding
    private var listSuggestion = listOf<City>()

    @Inject
    lateinit var citiesSuggestionService: ICitiesService

    @Inject
    lateinit var weatherService: IWeatherService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        UIHelper.setupUI(binding.root, this)
        setWeatherLocation()


        binding.text.addTextChangedListener {
            suggestCitiesName(it.toString())
        }
        binding.text.setOnItemClickListener { _, view, pos, _ ->
            val chosen = listSuggestion[pos]
            setWeatherLocation(chosen.id)

        }
    }


    private fun setWeatherLocation(id: Int = DEFAULT_ID) {
        Log.i(TAG, "setWeatherLocation: $id")
        weatherService.getWeather(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it != null) {
                    val fragment = WeatherFragment.newInstance(it)
                    val trans = supportFragmentManager.beginTransaction()
                    trans.replace(binding.frame.id, fragment)
                        .addToBackStack(it.city).commit()

                }
            },
                {
                    Toast.makeText(this, "Không tìm thấy thành phố bạn chọn", Toast.LENGTH_SHORT)
                        .show()
                    setWeatherLocation()
                }

            )
    }

    private fun suggestCitiesName(namePrefix: String) {
        citiesSuggestionService.suggest(namePrefix)
            .subscribeOn(Schedulers.io())//fetch
            .observeOn(AndroidSchedulers.mainThread())//updateUI
            .subscribe ({ suggestion ->
                if (suggestion != null) {
                    val adapter = ArrayAdapter(
                        this@MainActivity,
                        R.layout.dropdown_list,
                        suggestion.getListSuggestion()
                    )
                    binding.text.setAdapter(adapter)
                    adapter.notifyDataSetChanged()
                    listSuggestion = suggestion.data
                }
            },{
                it.printStackTrace()
            })
    }
}