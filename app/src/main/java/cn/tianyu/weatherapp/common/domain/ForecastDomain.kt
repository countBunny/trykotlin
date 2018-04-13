package cn.tianyu.weatherapp.common.domain

import cn.tianyu.weatherapp.bean.ForecastResult
import java.text.DateFormat
import java.util.*
import cn.tianyu.weatherapp.bean.Forecast as ModelForecast

data class ForecastList(val city: String, val country: String, val dailyForecast: List<ForecastModel>)

data class ForecastModel(val date: String, val description: String, val high: Int, val low: Int)

public class ForecastDataMapper {

    fun convertFromDataModel(forecast: ForecastResult): ForecastList {
        return ForecastList(forecast.city.name, forecast.city.country,
                convertForecastListToDomain(forecast.list))
    }

    private fun convertForecastListToDomain(list: List<ModelForecast>): List<ForecastModel> {
        return list.map { convertForecastItemToDomain(it) }
    }

    private fun convertForecastItemToDomain(forecast: ModelForecast): ForecastModel {
        return ForecastModel(convertData(forecast.dt), forecast.weather[0].description,
                forecast.temp.max.toInt(), forecast.temp.min.toInt())
    }

    private fun convertData(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date * 1000)
    }

}