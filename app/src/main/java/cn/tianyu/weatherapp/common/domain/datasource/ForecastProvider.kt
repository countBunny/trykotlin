package cn.tianyu.weatherapp.common.domain.datasource

import cn.tianyu.weatherapp.common.db.ForecastDb
import cn.tianyu.weatherapp.common.domain.Forecast
import cn.tianyu.weatherapp.common.domain.ForecastList
import cn.tianyu.weatherapp.common.extensions.firstResult
import cn.tianyu.weatherapp.common.server.ForecastServer

class ForecastProvider(private val source: List<ForecastDataSource> = ForecastProvider.SOURCE) {

    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCE by lazy {
            listOf(ForecastDb(), ForecastServer())
        }
    }

    fun requestByCityName(cityName:String = "Beijing", days:Int = 2): ForecastList = requestToSources{
        val res = it.requestForecastByZipCode(cityName, todayTimeSpan())
        if (res!=null&&res.size()>=days) res else null
    }

    fun requestForecast(id: String): Forecast = requestToSources {
        it.requestDayForecast(id)
    }

    private fun <T:Any> requestToSources(f:(ForecastDataSource) -> T?): T = source.firstResult {
        f(it)
    }

    private fun todayTimeSpan() = System.currentTimeMillis()
}