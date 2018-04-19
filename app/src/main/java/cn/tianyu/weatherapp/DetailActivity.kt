package cn.tianyu.weatherapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.doAsync

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
//            val result = Request
        }
    }
}
