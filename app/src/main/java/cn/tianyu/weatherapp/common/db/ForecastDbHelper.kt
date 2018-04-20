package cn.tianyu.weatherapp.common.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import cn.tianyu.weatherapp.App
import org.jetbrains.anko.db.*

class ForecastDbHelper(ctx: Context = App.instance) : ManagedSQLiteOpenHelper(ctx, ForecastDbHelper.DB_NAME,
        null, ForecastDbHelper.DB_VERSION) {
    companion object {

        val DB_NAME = "forecast.db"
        val DB_VERSION = 1
        val instance by lazy {
            ForecastDbHelper()
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(CityForecastTable.NAME, true,
                CityForecastTable.ID to TEXT + PRIMARY_KEY,
                CityForecastTable.CITY to TEXT,
                CityForecastTable.COUNTRY to TEXT)

        db?.createTable(DayForecastTable.NAME, true,
                DayForecastTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                DayForecastTable.DATE to INTEGER,
                DayForecastTable.DESCRIPTION to TEXT,
                DayForecastTable.HIGH to INTEGER,
                DayForecastTable.LOW to INTEGER,
                DayForecastTable.ICON_URL to INTEGER,
                DayForecastTable.WEATHER_CODE to INTEGER,
                DayForecastTable.CITY_ID to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(CityForecastTable.NAME, true)
        db?.dropTable(DayForecastTable.NAME, true)
        onCreate(db)
    }
}
