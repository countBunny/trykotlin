package cn.tianyu.weatherapp.utils

import android.content.Context
import android.os.Build
import cn.tianyu.weatherapp.common.extensions.Preferences
import cn.tianyu.weatherapp.common.extensions.StringPreference
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

inline fun supportsLollipop(code: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        code()
    }
}

inline fun convertDateToMillis(pattern: String = "yyyy-MM-dd", date: String): Long {
    val df = SimpleDateFormat(pattern, Locale.getDefault())
    return df.parse(date).time
}

inline fun convertDate(date: Long): String {
    val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
    return df.format(date)
}

fun Long.toDateString(dateFormat: Int = DateFormat.MEDIUM): String {
    val dateFormat = DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return dateFormat.format(this)
}

private class NotNullSingleValueVar<T>() : ReadWriteProperty<Any?, T> {

    private var value: T? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: throw IllegalStateException("${property.name} not initialized")
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = if (this.value == null) value
        else throw IllegalStateException("${property.name} already initialized")
    }
}

object DelegateExt {
    fun <T> notNullSingleValue(): ReadWriteProperty<Any?, T> = NotNullSingleValueVar()

    fun stringPrefs(context: Context, name: String, default: String) =
            StringPreference(context, name, default)

    fun <T : Any> prefs(context: Context, name: String, default: T) =
            Preferences(context, name, default)
}