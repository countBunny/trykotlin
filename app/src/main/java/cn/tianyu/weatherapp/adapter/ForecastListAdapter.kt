package cn.tianyu.weatherapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import cn.tianyu.weatherapp.R
import cn.tianyu.weatherapp.common.domain.ForecastList
import cn.tianyu.weatherapp.common.domain.ForecastModel
import cn.tianyu.weatherapp.utils.ctx
import org.jetbrains.anko.find

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

class ForecastListAdapter2(val forecastList: ForecastList,
                           val itemClick: (ForecastModel) -> Unit) :
        RecyclerView.Adapter<ForecastListAdapter2.ViewHolder>() {

    class ViewHolder(view: View, val itemClick: (ForecastModel) -> Unit) : RecyclerView.ViewHolder(view) {
        private val iconView: ImageView

        private val dateView: TextView

        private val descriptionView: TextView

        private val maxTemperatureView: TextView

        private val minTemperatureView: TextView

        init {
            iconView = view.find(R.id.icon)
            dateView = view.find(R.id.date)
            descriptionView = view.find(R.id.description)
            maxTemperatureView = view.find(R.id.maxTemperature)
            minTemperatureView = view.find(R.id.minTemperature)

        }

        fun bindForecast(forecast: ForecastModel) {
            with(forecast) {
                iconView.setImageResource(resId)
                dateView.text = date
                descriptionView.text = description
                maxTemperatureView.text = "${high.toString()}"
                minTemperatureView.text = "${low.toString()}"
                itemView.setOnClickListener { itemClick(forecast) }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.ctx)
                .inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun getItemCount(): Int = forecastList.size()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        with(forecastList[position]) {
//            holder.textView.text = "$date - $description - $high/$low"
//        }
        holder.bindForecast(forecastList[position])
    }

    interface OnItemClickListener {

        operator fun invoke(forecast: ForecastModel)
    }

}

