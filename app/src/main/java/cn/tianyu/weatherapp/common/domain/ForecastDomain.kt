package cn.tianyu.weatherapp.common.domain

import cn.tianyu.weatherapp.bean.Forecast as ModelForecast

data class ForecastList(val cityId: String, val city: String, val country: String, val dailyForecast: List<Forecast>) {

    operator fun get(position: Int) = dailyForecast[position]

    fun size() = dailyForecast.size
}

data class Forecast(val weatherCode: Int, val date: Long, val description: String, val high: Int, val low: Int,
                    val resId: Int)
