package com.example.auksion2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auksion2.data.RequestLotsBody
import com.example.auksion2.data.ResponseLotsBody
import com.example.auksion2.data.lot.LotBean
import com.example.auksion2.data.lot.PostDetailReq
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {
    private val reopsitory = SharedRepository()
    private val _getLotsByLiveData = MutableLiveData<ResponseLotsBody?>()
    private val _getLotsDetailByLiveData = MutableLiveData<LotBean?>()

    val getLotsByLiveData: LiveData<ResponseLotsBody?> = _getLotsByLiveData
    val getLotDetailByLiveData: LiveData<LotBean?> = _getLotsDetailByLiveData

    fun refreshData(body: RequestLotsBody) {
        viewModelScope.launch(Dispatchers.Main) {
            val response = reopsitory.getAllLots(body)
            _getLotsByLiveData.postValue(response)
        }
    }

    fun loadLotDetail(body: PostDetailReq) {
        viewModelScope.launch {
            val res = reopsitory.getLotDetail(body)
            _getLotsDetailByLiveData.postValue(res)
        }
    }

}