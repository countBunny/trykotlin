package cn.tianyu.weatherapp.common.server

import android.util.Log
import cn.tianyu.weatherapp.bean.ForecastResult
import com.google.gson.Gson
import org.json.JSONObject

class ForecastRequest(val zipCode: String = "beijing") {

    companion object {
        private val APP_ID = "yiyiqgupvurxe2bf"
//https://api.seniverse.com/v3/weather/daily.json?key=yiyiqgupvurxe2bf

        private val MOK_DOMAIN = "https://api.seniverse.com/v3/"
        private val URL = MOK_DOMAIN +
                "weather/daily.json?language=zh-Hans&unit=c&start=0&days=5"

        private val COMPLETE_URL = "${URL}&key=${APP_ID}&location="

    }

    fun execute():ForecastResult{
        val forecastJsonStr = java.net.URL(COMPLETE_URL + zipCode).readText()
        Log.d(javaClass.simpleName, "$forecastJsonStr")
        val result = JSONObject(forecastJsonStr).optJSONArray("results").optJSONObject(0)
        return Gson().fromJson(result.toString(), ForecastResult::class.java)
    }
}