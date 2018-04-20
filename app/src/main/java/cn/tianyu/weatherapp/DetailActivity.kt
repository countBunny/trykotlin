package cn.tianyu.weatherapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import cn.tianyu.weatherapp.common.domain.Forecast
import cn.tianyu.weatherapp.common.domain.RequestDayForecastCommand
import cn.tianyu.weatherapp.common.extensions.color
import cn.tianyu.weatherapp.common.extensions.textColor
import cn.tianyu.weatherapp.utils.getWeatherDrawable
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread

class DetailActivity : AppCompatActivity() {

    companion object {
        val ID = "DetailActivity:id"
        val CITY_NAME = "DetailActivity:cityName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        titleDetail.text = intent.getStringExtra(CITY_NAME)

        doAsync {
            val result = RequestDayForecastCommand(intent.getStringExtra(ID)).execute()
            uiThread { bindForecast(result) }
            Log.e("DetailActivity", "result = " + result.toString())
        }
    }

    private fun bindForecast(forecast: Forecast) = with(forecast) {
        val icon = find(R.id.ivIcon) as ImageView
        icon.setImageResource(getWeatherDrawable(weatherCode))
        find<TextView>(R.id.weatherDescription).text = description
        bindWeather(high to find(R.id.maxTemperature), low to find(R.id.minTemperature))
        supportActionBar?.subtitle = description
    }

    private fun bindWeather(vararg views: Pair<Int, TextView>) = views.forEach {
        it.second.text = "${it.first}"
        it.second.textColor = color(when (it.first) {
            in -50..0 -> android.R.color.holo_red_dark
            in 0..15 -> android.R.color.holo_orange_dark
            else -> android.R.color.holo_green_dark
        })
    }

}
