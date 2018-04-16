package cn.tianyu.weatherapp.bean

data class ForecastResult(val location: City,
                          val daily: List<Forecast>)

data class City(val id: String, val name: String, val coord: Coordinates, val country: String,
                val population: Int)

data class Coordinates(val lon: Float, val lat: Float)

data class Forecast(val date: String, val text_day: String, val code_day: Int, val text_night: String,
                    val code_night: Int, val high: Int, val low: Int, val wind_speed: Int,
                    val wind_scale: Int, val iconResId: Int)
