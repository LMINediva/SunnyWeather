package com.sunnyweather.android.logic.model

import com.google.gson.annotations.SerializedName
import java.util.Date

/**
 * 未来几天天气信息的数据模型
 */
data class DailyResponse(val status: String, val result: Result) {

    // 获取到的未来几天的天气信息结果
    data class Result(val daily: Daily)

    // 当前地区未来几天的天气信息
    data class Daily(
        val temperature: List<Temperature>, val skycon: List<Skycon>,
        @SerializedName("life_index") val lifeIndex: LifeIndex
    )

    // 未来几天的温度值
    data class Temperature(val max: Float, val min: Float)

    // 未来几天的天气状况
    data class Skycon(val value: String, val date: Date)

    // 生活指数
    data class LifeIndex(
        val coldRisk: List<LifeDescription>, val carWashing:
        List<LifeDescription>, val ultraviolet: List<LifeDescription>,
        val dressing: List<LifeDescription>
    )

    // 生活指数的相关描述
    data class LifeDescription(val desc: String)

}