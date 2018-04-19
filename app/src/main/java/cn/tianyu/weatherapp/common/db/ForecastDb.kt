package cn.tianyu.weatherapp.common.db

import cn.tianyu.weatherapp.common.domain.ForecastList
import cn.tianyu.weatherapp.common.domain.datasource.ForecastDataSource
import cn.tianyu.weatherapp.common.extensions.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class ForecastDb(private val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
                 private val dataMapper: DbDataMapper = DbDataMapper()) : ForecastDataSource{

    override fun requestDayForecast(id: String)= forecastDbHelper.use {
        val forecast = select(DayForecastTable.NAME).byId(DayForecastTable.CITY_ID,id)
                .parseOpt{ DayForecast(HashMap(it))}
        if (forecast != null) {
            dataMapper.convertDayToDomain(forecast)
        } else {
            null
        }
    }

    override fun requestForecastByZipCode(cityId: String, date: Long) = forecastDbHelper.use {

        val dailyRequest = "${DayForecastTable.CITY_ID} = {id} AND ${DayForecastTable.DATE} > {date}"// AND ${DayForecastTable.DATE} == ?
        val dailyForecast = select(DayForecastTable.NAME)
                .whereArgs(dailyRequest, "id" to cityId, "date" to date)
                .parseList { DayForecast(HashMap(it)) }

        val city = select(CityForecastTable.NAME)
                .whereSimple("${CityForecastTable.ID} = ?", cityId)
//                .parseOpt
                .parseOpt(classParser<CityForecast>())
        if (city != null) dataMapper.convertToDomain(city) else null
    }

    fun saveForecast(forecast:ForecastList) = forecastDbHelper.use {
        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)

        with(dataMapper.convertFromDomain(forecast)) {
            insert(CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach {
                insert(DayForecastTable.NAME, *it.map.toVarargArray())
            }
        }
    }
}