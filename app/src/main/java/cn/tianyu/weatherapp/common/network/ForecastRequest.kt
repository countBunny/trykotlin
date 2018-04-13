package cn.tianyu.weatherapp.common.network

import android.util.Log
import cn.tianyu.weatherapp.bean.ForecastResult
import com.google.gson.Gson

class ForecastRequest(val zipCode: String) {

    companion object {
        private val APP_ID = "b6907d289e10d714a6e88b30761fae22"
//http://samples.openweathermap.org/data/2.5/forecast/daily?zip=94040&appid=

        private val MOK_DOMAIN = "http://samples.openweathermap.org/data/2.5"
        private val PROD_DOMAIN = "http://api.openweathermap.org/data/2.5"
        private val URL = MOK_DOMAIN +
                "forecast/daily?mode=json&units=metric&cnt=7"

        private val COMPLETE_URL = "$URL&APPID=$APP_ID&q="

    }

    fun execute():ForecastResult{
        val forecastJsonStr = java.net.URL(COMPLETE_URL + zipCode).readText()
        Log.d(javaClass.simpleName, "$forecastJsonStr")
        return Gson().fromJson(forecastJsonStr, ForecastResult::class.java)
    }
}