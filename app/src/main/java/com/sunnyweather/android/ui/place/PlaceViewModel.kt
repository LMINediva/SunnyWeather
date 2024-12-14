package com.sunnyweather.android.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.sunnyweather.android.logic.Repository
import com.sunnyweather.android.logic.model.Place

/**
 * 城市信息ViewModel
 */
class PlaceViewModel : ViewModel() {

    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()

    val placeLiveData = searchLiveData.switchMap { query ->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }

    // 保存选中的城市名
    fun savePlace(place: Place) = Repository.savePlace(place)

    // 获取已经保存的选中城市名
    fun getSavedPlace() = Repository.getSavedPlace()

    // 判断选择的城市名是否保存
    fun isPlaceSaved() = Repository.isPlaceSaved()
}