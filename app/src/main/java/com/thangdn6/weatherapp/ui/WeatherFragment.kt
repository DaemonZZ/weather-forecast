package com.thangdn6.weatherapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.thangdn6.weatherapp.databinding.WeatherFragmentBinding
import com.thangdn6.weatherapp.model.weather.Weather
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment private constructor() : Fragment() {

    companion object {
        private const val TAG = "ThangDN6 - WeatherFragment"
        fun newInstance(data: Weather): WeatherFragment {
            val frg = WeatherFragment()
            val args = Bundle().apply {
                putSerializable("data", data)
            }
            frg.arguments = args
            return frg
        }
    }

    private lateinit var binding: WeatherFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WeatherFragmentBinding.inflate(inflater)
        val data = arguments?.getSerializable("data")
        if (data is Weather) {
            binding.weather = data
        }

        return binding.root
    }
}