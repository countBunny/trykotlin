package cn.tianyu.weatherapp

import android.app.Application
import cn.tianyu.weatherapp.utils.DelegateExt

class App : Application() {
    companion object {
        var instance:App by DelegateExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}