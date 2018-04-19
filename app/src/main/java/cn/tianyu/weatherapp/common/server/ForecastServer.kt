package cn.tianyu.weatherapp.common.server

import cn.tianyu.weatherapp.common.db.ForecastDb
import cn.tianyu.weatherapp.common.domain.ForecastList
import cn.tianyu.weatherapp.common.domain.datasource.ForecastDataSource

class ForecastServer(private val dataMapper: ServerDataMapper = ServerDataMapper(),
                     private val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource {
    override fun requestForecastByZipCode(cityName: String, date: Long): ForecastList? {
        val result = ForecastRequest(cityName).execute()
        val converted = dataMapper.convertToDomain(cityName, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(cityName, date)
    }

    override fun requestDayForecast(id: String) = throw UnsupportedOperationException()
}