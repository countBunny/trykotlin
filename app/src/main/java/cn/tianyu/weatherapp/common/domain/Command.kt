package cn.tianyu.weatherapp.common.domain

public interface Command<T> {
    fun execute():T
}