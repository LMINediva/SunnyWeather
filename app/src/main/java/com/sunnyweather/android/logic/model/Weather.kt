package com.sunnyweather.android.logic.model

/**
 * 天气类，包含实时天气和未来几天天气信息
 */
data class Weather(val realtime: RealtimeResponse.Realtime, val daily: DailyResponse.Daily)