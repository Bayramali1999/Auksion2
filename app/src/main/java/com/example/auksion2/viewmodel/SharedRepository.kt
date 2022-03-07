package com.example.auksion2.viewmodel

import com.example.auksion2.common.Constant
import com.example.auksion2.data.RequestLotsBody
import com.example.auksion2.data.ResponseLotsBody

class SharedRepository {
    suspend fun getAllLots(body: RequestLotsBody): ResponseLotsBody? {
        val req = Constant.apiClient.getAllLots(body)
        if (req.isSuccessful) {
            return req.body()
        }
        return null
    }

}
