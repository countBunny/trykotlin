package cn.tianyu.weatherapp.bean

import android.content.Context
import android.widget.Toast

class Person(name: String, surname: String) : Animal(name) {

    init {
        //构造函数可以在这里写
    }
}

open class Animal(name: String)

fun add(x: Int, y: Int): Int {
    return x + y
}

fun adddirect(x: Int, y: Int): Int = x + y

fun toast(context: Context, message:String, length:Int=Toast.LENGTH_SHORT){
    Toast.makeText(context, message, length).show()
}