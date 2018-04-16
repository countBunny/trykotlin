package cn.tianyu.weatherapp.common.domain

import cn.tianyu.weatherapp.common.network.ForecastRequest

class RequestForecastCommand(val zipCode: String = "beijing") : Command<ForecastList> {

    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}