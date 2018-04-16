package cn.tianyu.weatherapp

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import cn.tianyu.weatherapp.adapter.ForecastListAdapter2
import cn.tianyu.weatherapp.common.domain.RequestForecastCommand
import cn.tianyu.weatherapp.utils.supportsLollipop
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thurs 6/26 - Rainy - 18/11",
            "Fri 6/27 - Foggy - 21/10",
            "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
            "Sun 6/29 - Sunny - 20/7"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        message.text = "Hello Kotlin!"
        niceToast(message = "Hello", length = Toast.LENGTH_LONG)
        val forecastList:RecyclerView = find(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)
//        forecastList.adapter = ForecastListAdapter(forecastList)
        doAsync {
//            val url = "http://api.openweathermap.org/data/2.5/weather?id=2172797"

//            Request(url).run()
            val result = RequestForecastCommand().execute()
            uiThread {
                forecastList.adapter = ForecastListAdapter2(result){
                    toast(it.date)
                }
                longToast("Request performed")
            }
        }
        //practice
        /*val f1 = Forecast(Date(), 27.5f, "shiny day")
        val f2 = f1.copy(temperature = 30f)
        val (date, temperature, details) = f1
        val date1 = f1.component1()
        val temperature2 = f1.component2()
        val details2 = f1.component3()*/
        supportsLollipop {
            window.statusBarColor=Color.BLACK
        }
    }

    inline fun Context.niceToast(message: String, tag: String =javaClass.simpleName,
                  length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, "[$tag] $message", length).show()
    }
}