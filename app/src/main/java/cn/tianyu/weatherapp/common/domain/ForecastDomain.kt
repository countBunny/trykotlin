package cn.tianyu.weatherapp.common.domain

import cn.tianyu.weatherapp.bean.ForecastResult
import cn.tianyu.weatherapp.utils.getWeatherDrawable
import java.text.DateFormat
import java.util.*
import cn.tianyu.weatherapp.bean.Forecast as ModelForecast

data class ForecastList(val city: String, val country: String, val dailyForecast: List<ForecastModel>){

    operator fun get(position: Int) = dailyForecast[position]

    fun size() = dailyForecast.size
}

data class ForecastModel(val date: String, val description: String, val high: Int, val low: Int,
                         val resId:Int)

public class ForecastDataMapper {

    fun convertFromDataModel(forecast: ForecastResult): ForecastList {
        return ForecastList(forecast.location.name, forecast.location.country,
                convertForecastListToDomain(forecast.daily))
    }

    private fun convertForecastListToDomain(list: List<ModelForecast>): List<ForecastModel> {
        return list.map { convertForecastItemToDomain(it) }
    }

    private fun convertForecastItemToDomain(forecast: ModelForecast): ForecastModel {
        return ForecastModel(forecast.date, forecast.text_day,
                forecast.high.toInt(), forecast.low.toInt(), getWeatherDrawable(forecast.code_day))
    }

    private fun convertData(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date * 1000)
    }

}