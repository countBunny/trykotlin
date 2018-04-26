package cn.tianyu.weatherapp.common.extensions

import android.content.Context
import android.view.View
import android.widget.TextView

var TextView.textColor: Int
    get() = currentTextColor
    set(v) = setTextColor(v)

val View.ctx: Context
    get() = context

fun View.slideExit(){
    if (translationY == 0f) animate().translationY(-height.toFloat())
}

fun View.slideEnter(){
    if (translationY < 0f) animate().translationY(0f)
}