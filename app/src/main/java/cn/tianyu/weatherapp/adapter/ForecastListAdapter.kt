package cn.tianyu.weatherapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import cn.tianyu.weatherapp.common.domain.ForecastList

class ForecastListAdapter(val items: List<String>) :
        RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(TextView(parent!!.context))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items[position]
    }

}
class ForecastListAdapter2(val forecastList: ForecastList) :
        RecyclerView.Adapter<ForecastListAdapter2.ViewHolder>() {

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(TextView(parent!!.context))
    }

    override fun getItemCount(): Int = forecastList.dailyForecast.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(forecastList.dailyForecast[position]){
            holder.textView.text = "$date - $description - $high/$low"
        }
    }

}