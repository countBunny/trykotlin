package cn.tianyu.weatherapp.common.db

import cn.tianyu.weatherapp.common.domain.Forecast
import cn.tianyu.weatherapp.common.domain.ForecastList

class DbDataMapper {

    fun convertFromDomain(forecast: ForecastList) = with(forecast) {
        val daily = dailyForecast.map { convertDayFromDomain(city, it) }
        CityForecast(city, city, country, daily)
    }

    private fun convertDayFromDomain(cityId: String, forecast: Forecast) = with(forecast) {
        DayForecast(weatherCode, date, description, high, low, resId, cityId)
    }

    fun convertToDomain(forecast: CityForecast) = with(forecast) {
        val daily = dailyForecast.map { convertDayToDomain(it) }
        ForecastList(_id, city, country, daily)
    }

    fun convertDayToDomain(dayForecast: DayForecast)= with(dayForecast) {
        Forecast(dayCode, date, description, high, low, iconRes)
    }
}