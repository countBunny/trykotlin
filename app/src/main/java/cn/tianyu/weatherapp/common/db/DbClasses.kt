package cn.tianyu.weatherapp.common.db

class CityForecast(val map: MutableMap<String, Any?>, val dailyForecast: List<DayForecast>) {

    var _id: String by map

    var city: String by map

    var country: String by map

    constructor(id: String, city: String, country: String, dailyForecast: List<DayForecast>)
            : this(HashMap(), dailyForecast) {
        this._id = id
        this.city = city
        this.country = country
    }
}

class DayForecast(var map: MutableMap<String, Any?>) {

    var _id: String by map
    var date: Long by map //存毫秒数容易比较大小

    var description: String by map

    var high: Int by map

    var low: Int by map

    var dayCode:Int by map

    var iconRes: Int by map

    var cityId: String by map

    constructor(dayCode: Int, date: Long, description: String, high: Int, low: Int, iconRes: Int, cityId: String)
            : this(HashMap()) {
        this.date = date
        this.description = description
        this.high = high
        this.low = low
        this.dayCode = dayCode
        this.iconRes = iconRes
        this.cityId = cityId
    }
}