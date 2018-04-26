package cn.tianyu.weatherapp.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import cn.tianyu.weatherapp.R
import cn.tianyu.weatherapp.utils.DelegateExt
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.single_toolbar.*

class SettingsActivity : AppCompatActivity() {

    companion object {
        val CITY_NAME = "cityName"

        val DEFAULT_PINYIN = "北京"
    }

    var mCityName: String by DelegateExt.stringPrefs(this, CITY_NAME, DEFAULT_PINYIN)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        cityName.setText(mCityName)

    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        android.R.id.home -> {
            onBackPressed(); true
        }
        else -> false
    }

    override fun onBackPressed() {
        super.onBackPressed()
        mCityName = cityName.text.toString()
    }
}