package cn.tianyu.weatherapp.common.domain

import cn.tianyu.weatherapp.bean.ForecastResult
import cn.tianyu.weatherapp.utils.convertDateToMillis
import cn.tianyu.weatherapp.utils.getWeatherDrawable
import cn.tianyu.weatherapp.bean.Forecast as ModelForecast

data class ForecastList(val cityId:String, val city: String, val country: String, val dailyForecast: List<Forecast>){

    operator fun get(position: Int) = dailyForecast[position]

    fun size() = dailyForecast.size
}

data class Forecast(val date: Long, val description: String, val high: Int, val low: Int,
                    val resId:Int)

public class ForecastDataMapper {

    fun convertFromDataModel(forecast: ForecastResult): ForecastList {
        return ForecastList(forecast.location.id, forecast.location.name, forecast.location.country,
                convertForecastListToDomain(forecast.daily))
    }

    private fun convertForecastListToDomain(list: List<ModelForecast>): List<Forecast> {
        return list.map { convertForecastItemToDomain(it) }
    }

    private fun convertForecastItemToDomain(forecast: ModelForecast): Forecast {
        return Forecast(convertDateToMillis(date = forecast.date), forecast.text_day,
                forecast.high.toInt(), forecast.low.toInt(), getWeatherDrawable(forecast.code_day))
    }

}