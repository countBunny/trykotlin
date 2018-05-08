package cn.tianyu.weatherapp.ui.activity

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.widget.Toast
import cn.tianyu.weatherapp.R
import cn.tianyu.weatherapp.common.domain.RequestForecastCommand
import cn.tianyu.weatherapp.ui.adapter.ForecastListAdapter2
import cn.tianyu.weatherapp.ui.interfaces.IToolbarManager
import cn.tianyu.weatherapp.utils.DelegateExt
import cn.tianyu.weatherapp.utils.supportsLollipop
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity(), IToolbarManager{

    override val toolbar by lazy {
        find<Toolbar>(R.id.toolbar)
    }
    /*private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thurs 6/26 - Rainy - 18/11",
            "Fri 6/27 - Foggy - 21/10",
            "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
            "Sun 6/29 - Sunny - 20/7"
    )*/

    val mCityName:String by DelegateExt.stringPrefs(this, SettingsActivity.CITY_NAME, SettingsActivity.DEFAULT_PINYIN)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        message.text = "Hello Kotlin!"
        niceToast(message = "Hello", length = Toast.LENGTH_LONG)
//        val forecastList:RecyclerView = find(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)
        attachToScroll(forecastList)
//        forecastList.adapter = ForecastListAdapter(forecastList)

        //practice
        /*val f1 = Forecast(Date(), 27.5f, "shiny day")
        val f2 = f1.copy(temperature = 30f)
        val (date, temperature, details) = f1
        val date1 = f1.component1()
        val temperature2 = f1.component2()
        val details2 = f1.component3()*/
        supportsLollipop {
            window.statusBarColor = Color.BLACK
        }
        toolbar.title = "天气"
    }

    override fun onResume() {
        super.onResume()
        toolbar.title = mCityName
        loadforecast()
    }

    private fun loadforecast() = doAsync {
        //            val url = "http://api.openweathermap.org/data/2.5/weather?id=2172797"

//            Request(url).run()
        val result = RequestForecastCommand(mCityName).execute()
        uiThread {
            forecastList.adapter = ForecastListAdapter2(result) {
                startActivity<DetailActivity>(DetailActivity.ID to it.date.toString(),
                        DetailActivity.CITY_NAME to result.city)
            }
            longToast("Request performed")
        }
    }

    inline fun Context.niceToast(message: String, tag: String = javaClass.simpleName,
                                 length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, "[$tag] $message", length).show()
    }
}