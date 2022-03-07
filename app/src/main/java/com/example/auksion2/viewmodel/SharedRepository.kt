package com.example.auksion2.viewmodel

import com.example.auksion2.common.Constant
import com.example.auksion2.data.RequestLotsBody
import com.example.auksion2.data.ResponseLotsBody
import com.example.auksion2.data.lot.LotBean
import com.example.auksion2.data.lot.PostDetailReq

class SharedRepository {
    suspend fun getAllLots(body: RequestLotsBody): ResponseLotsBody? {
        val req = Constant.apiClient.getAllLots(body)
        if (req.isSuccessful) {
            return req.body()
        }
        return null
    }

    suspend fun getLotDetail(body: PostDetailReq): LotBean? {
        val req = Constant.apiClient.getLotDetails(body)
        if (req.isSuccessful) {
            return req.body()
        }
        return null
    }

}
