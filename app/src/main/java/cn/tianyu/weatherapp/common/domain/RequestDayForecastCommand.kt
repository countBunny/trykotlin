package cn.tianyu.weatherapp.common.domain

import cn.tianyu.weatherapp.common.domain.datasource.ForecastProvider

class RequestDayForecastCommand (val id:String,
                                 private val forecastProvider: ForecastProvider = ForecastProvider())
    :Command<Forecast>{
    override fun execute() = forecastProvider.requestForecast(id)
}