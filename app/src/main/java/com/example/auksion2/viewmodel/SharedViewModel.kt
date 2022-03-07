package com.example.auksion2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auksion2.data.RequestLotsBody
import com.example.auksion2.data.ResponseLotsBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {
    private val reopsitory = SharedRepository()
    private val _getLotsByLiveData = MutableLiveData<ResponseLotsBody?>()

    val getLotsByLiveData: LiveData<ResponseLotsBody?> = _getLotsByLiveData

    fun refreshData(body: RequestLotsBody) {
        viewModelScope.launch(Dispatchers.Main) {
            val response = reopsitory.getAllLots(body)
            _getLotsByLiveData.postValue(response)
        }
    }

}