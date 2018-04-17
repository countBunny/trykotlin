package cn.tianyu.weatherapp.common.db

object CityForecastTable{
    val NAME = "CityForecast"
    val CITY = "city"
    val ID = "_id"
    val COUNTRY = "country"
}

object DayForecastTable{
    val NAME = "DayForecast"
    val ID = "_id"
    val DATE = "date"
    val DESCRIPTION = "description"
    val HIGH = "high"
    val LOW = "low"
    val ICON_URL = "iconUrl"
    val CITY_ID = "cityId"
}