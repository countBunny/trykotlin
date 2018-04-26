package cn.tianyu.weatherapp.common.server

import android.util.Log
import cn.tianyu.weatherapp.common.db.ForecastDb
import cn.tianyu.weatherapp.common.domain.ForecastList
import cn.tianyu.weatherapp.common.domain.datasource.ForecastDataSource
import java.net.URLEncoder

class ForecastServer(private val dataMapper: ServerDataMapper = ServerDataMapper(),
                     private val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource {
    companion object {
        val TAG:String = "ForecastServer"
    }

    override fun requestForecastByZipCode(cityName: String, date: Long): ForecastList? {
        val convertedCityName = URLEncoder.encode(cityName, Charsets.UTF_8.name())
        Log.e(TAG, "converted name="+convertedCityName+" charset is "+ Charsets.UTF_8.name())
        val result = ForecastRequest(convertedCityName).execute()
        val converted = dataMapper.convertToDomain(cityName, result)
        forecastDb.saveForecast(converted)
        Log.e("ForecastServer", " current millis = " + date)
        return forecastDb.requestForecastByZipCode(cityName, date)
    }

    override fun requestDayForecast(id: String) = throw UnsupportedOperationException()

}