package cn.tianyu.weatherapp

import cn.tianyu.weatherapp.common.domain.Forecast
import cn.tianyu.weatherapp.common.domain.datasource.ForecastDataSource
import cn.tianyu.weatherapp.common.domain.datasource.ForecastProvider
import junit.framework.Assert
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class ProviderTest{

    @Test
    fun testForecastProvider(){
        val ds = mock(ForecastDataSource::class.java)
        `when`(ds.requestDayForecast("0"))
                .then {
                    Forecast(0,0,"desc",20,0,R.mipmap.we0)
                }
        val server = mock(ForecastDataSource::class.java)
        `when`(server.requestDayForecast("0"))
        val provider = ForecastProvider(listOf(ds))
        Assert.assertNotNull(provider.requestForecast("0"))
    }
}