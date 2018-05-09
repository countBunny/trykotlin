package cn.tianyu.weatherapp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import cn.tianyu.weatherapp.R
import cn.tianyu.weatherapp.common.domain.Forecast
import cn.tianyu.weatherapp.common.domain.ForecastList
import cn.tianyu.weatherapp.common.extensions.ctx
import cn.tianyu.weatherapp.utils.convertDate
import cn.tianyu.weatherapp.utils.getWeatherDrawable
import kotlinx.android.synthetic.main.item_forecast.view.*

class ForecastListAdapter(val items: List<String>) :
        RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TextView(parent.context))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items[position]
    }

}

class ForecastListAdapter2(val forecastList: ForecastList,
                           val itemClick: (Forecast) -> Unit) :
        RecyclerView.Adapter<ForecastListAdapter2.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx)
                .inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick)
    }

    class ViewHolder(view: View, val itemClick: (Forecast) -> Unit) : RecyclerView.ViewHolder(view) {

        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                itemView.icon.setImageResource(getWeatherDrawable(weatherCode))
                itemView.date.text = convertDate(date)
                itemView.description.text = description
                itemView.maxTemperature.text = "${high.toString()}"
                itemView.minTemperature.text = "${low.toString()}"
                itemView.setOnClickListener { itemClick(forecast) }
            }
        }

    }

    override fun getItemCount(): Int = forecastList.size()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        with(forecastList[position]) {
//            holder.textView.text = "$date - $description - $high/$low"
//        }
        holder.bindForecast(forecastList[position])
    }

    interface OnItemClickListener {

        operator fun invoke(forecast: Forecast)
    }

}

