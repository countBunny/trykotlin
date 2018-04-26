package cn.tianyu.weatherapp.common.domain

import cn.tianyu.weatherapp.common.domain.datasource.ForecastProvider

class RequestForecastCommand(val zipCode: String = "beijing",
                             private val forecastProvider:ForecastProvider = ForecastProvider()) : Command<ForecastList> {

    override fun execute() = forecastProvider.requestByCityName(zipCode)
}