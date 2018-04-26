package cn.tianyu.weatherapp.bean

import android.content.Context
import android.widget.TextView
import android.widget.Toast
import cn.tianyu.weatherapp.ui.App

class Person(name: String, surname: String = "") : Animal(name) {

    var name: String = ""
        get() = field.toUpperCase()
        set(value) {
            field = "Name:$value"
        }

    init {
        //构造函数可以在这里写
        this.name = name
    }
}

class Person2{

    constructor()
}

open class Animal(name: String)

fun add(x: Int, y: Int): Int {
    return x + y
}

fun adddirect(x: Int, y: Int): Int = x + y

fun toast(context: Context = App.instance, message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, message, length).show()
}

fun practice1() {
    var i: Int = 7
    val d: Double = i.toDouble()
    val c: Char = 'c'
    i = c.toInt()
    val person = Person("name")
    val name = person.name
}

public var TextView.text: CharSequence
    get() = getText()
    set(v) = setText(v)