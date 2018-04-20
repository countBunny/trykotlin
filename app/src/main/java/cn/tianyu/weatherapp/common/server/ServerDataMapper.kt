package cn.tianyu.weatherapp.common.server

import android.util.Log
import cn.tianyu.weatherapp.bean.Forecast
import cn.tianyu.weatherapp.bean.ForecastResult
import cn.tianyu.weatherapp.common.domain.ForecastList
import cn.tianyu.weatherapp.utils.convertDateToMillis
import cn.tianyu.weatherapp.utils.getWeatherDrawable
import cn.tianyu.weatherapp.common.domain.Forecast as ModelForecast

class ServerDataMapper {

    fun convertToDomain(cityName: String, forecast: ForecastResult) = with(forecast) {
        ForecastList(location.id, location.name, location.country, convertForecastListToDomain(daily))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.mapIndexed { i, forecast ->
            val date = convertDateToMillis(date = forecast.date)
            Log.e("ForecastDataMapper", "date = " + date)
            ModelForecast(forecast.code_day, date, forecast.text_day, forecast.high, forecast.low, getWeatherDrawable(forecast.code_day))
        }
    }
}