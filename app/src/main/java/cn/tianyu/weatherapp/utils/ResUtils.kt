package cn.tianyu.weatherapp.utils

import cn.tianyu.weatherapp.R

inline fun getWeatherDrawable(weather:Int):Int{
    return when(weather){
        0 -> R.mipmap.weather0
        1 -> R.mipmap.we1
        2 -> R.mipmap.we2
        3 -> R.mipmap.we3
        4 -> R.mipmap.we4
        5 -> R.mipmap.we5
        6 -> R.mipmap.we6
        7 -> R.mipmap.we7
        8 -> R.mipmap.we8
        9 -> R.mipmap.we9
        10 -> R.mipmap.we10
        11 -> R.mipmap.we11
        12 -> R.mipmap.we12
        13 -> R.mipmap.we13
        14 -> R.mipmap.we14
        15 -> R.mipmap.we15
        16 -> R.mipmap.we16
        17 -> R.mipmap.we17
        18 -> R.mipmap.we18
        19 -> R.mipmap.we19
        21 -> R.mipmap.we20
        22 -> R.mipmap.we21
        23 -> R.mipmap.we22
        24 -> R.mipmap.we23
        25 -> R.mipmap.we24
        26 -> R.mipmap.we25
        27 -> R.mipmap.we26
        28 -> R.mipmap.we27
        29 -> R.mipmap.we28
        30 -> R.mipmap.we29
        31 -> R.mipmap.we30
        32 -> R.mipmap.we31
        33 -> R.mipmap.we32
        34 -> R.mipmap.we33
        35 -> R.mipmap.we34
        36 -> R.mipmap.we35
        37 -> R.mipmap.we36
        38 -> R.mipmap.we37
        39 -> R.mipmap.we38
        else -> R.mipmap.we99
    }
}