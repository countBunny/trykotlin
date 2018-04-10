package cn.tianyu.weatherapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        message.text = "Hello Kotlin!"
        niceToast(message = "Hello", length = Toast.LENGTH_LONG)
    }

    fun niceToast(message: String, tag:String = MainActivity::class.java.getSimpleName(),
                  length:Int=Toast.LENGTH_SHORT){
        Toast.makeText(this, "[$tag] $message", length).show()
    }
}