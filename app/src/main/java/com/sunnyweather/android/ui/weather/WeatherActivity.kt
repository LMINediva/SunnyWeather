package com.sunnyweather.android.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.sunnyweather.android.R
import com.sunnyweather.android.logic.model.Weather
import com.sunnyweather.android.logic.model.getSky

/**
 * 城市天气信息展示界面
 */
class WeatherActivity : AppCompatActivity() {

    val viewModel by lazy { ViewModelProviders.of(this).get(WeatherViewModel::class.java) }
    private lateinit var placeName: TextView
    private lateinit var currentTemp: TextView
    private lateinit var currentSky: TextView
    private lateinit var currentAQI: TextView
    private lateinit var nowLayout: RelativeLayout
    private lateinit var forecastLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        placeName = findViewById(R.id.placeName)
        currentTemp = findViewById(R.id.currentTemp)
        currentSky = findViewById(R.id.currentSky)
        currentAQI = findViewById(R.id.currentAQI)
        nowLayout = findViewById(R.id.nowLayout)
        // 获取经度
        if (viewModel.locationLng.isEmpty()) {
            viewModel.locationLng = intent.getStringExtra("location_lng") ?: ""
        }
        // 获取纬度
        if (viewModel.locationLat.isEmpty()) {
            viewModel.locationLat = intent.getStringExtra("location_lat") ?: ""
        }
        // 获取城市名
        if (viewModel.placeName.isEmpty()) {
            viewModel.placeName = intent.getStringExtra("place_name") ?: ""
        }
    }

    private fun showWeatherInfo(weather: Weather) {
        // 城市名
        placeName.text = viewModel.placeName
        // 当前地区的实时天气信息
        val realtime = weather.realtime
        // 当前地区未来几天的天气信息
        val daily = weather.daily
        // 填充now.xml（实时天气）布局中的数据
        val currentTempText = "${realtime.temperature.toInt()} ℃"
        // 当前的温度
        currentTemp.text = currentTempText
        // 当前的天气情况
        currentSky.text = getSky(realtime.skycon).info
        // 当前的空气质量
        val currentPM25Text = "空气指数 ${realtime.airQuality.aqi.chn.toInt()}"
        currentAQI.text = currentPM25Text
        nowLayout.setBackgroundResource(getSky(realtime.skycon).bg)
        // 填充forecast.xml（预测天气）布局中的数据
        forecastLayout.removeAllViews()
        val days = daily.skycon.size
        for (i in 0 until days) {
            // 未来几天的天气情况
            val skycon = daily.skycon[i]
            // 未来几天的温度值
            val temperature = daily.temperature[i]
            val view = LayoutInflater.from(this).inflate(
                R.layout.forecast_item,
                forecastLayout, false
            )
            // 天气预报的日期
            val dateInfo = view.findViewById(R.id.dateInfo) as TextView
            // 天气的图标
            val skyIcon = view.findViewById(R.id.skyIcon) as ImageView
            // 天气的情况
            val skyInfo = view.findViewById(R.id.skyInfo) as TextView
        }
    }

}