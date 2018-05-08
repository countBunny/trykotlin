package cn.tianyu.weatherapp

import cn.tianyu.weatherapp.common.domain.ForecastList
import cn.tianyu.weatherapp.common.domain.datasource.ForecastDataSource
import cn.tianyu.weatherapp.common.domain.datasource.ForecastProvider
import junit.framework.Assert
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class ProviderTest{

    @Test
    fun testForecastProvider(){
        val ds = mock(ForecastDataSource::class.java)
        /*`when`(ds.requestDayForecast("0"))
                .then {
                    Forecast(0,0,"desc",20,0,R.mipmap.we0)
                }*/
        val server = mock(ForecastDataSource::class.java)
        `when`(server.requestForecastByZipCode(ArgumentMatchers.anyString(), ArgumentMatchers.anyLong()))
                .then {
                    ForecastList("0", "city","country", listOf())
                }
        val provider = ForecastProvider(listOf(ds, server))
//        Assert.assertNotNull(provider.requestForecast("0"))
        val result = provider.requestByCityName("0", 0)
        Assert.assertNotNull(result)
        println(result)
    }
}