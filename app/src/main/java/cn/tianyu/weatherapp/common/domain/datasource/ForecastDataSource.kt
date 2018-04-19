package cn.tianyu.weatherapp.common.domain.datasource

import cn.tianyu.weatherapp.common.domain.Forecast
import cn.tianyu.weatherapp.common.domain.ForecastList

interface ForecastDataSource {

    fun requestForecastByZipCode(cityName: String, date: Long):ForecastList?

    fun requestDayForecast(id:String): Forecast?
}