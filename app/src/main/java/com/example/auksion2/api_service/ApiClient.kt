package com.example.auksion2.api_service

import com.example.auksion2.data.RequestLotsBody
import com.example.auksion2.data.ResponseLotsBody
import com.example.auksion2.data.lot.LotBean
import com.example.auksion2.data.lot.PostDetailReq
import retrofit2.Response

class ApiClient(private val auksionService: AuksionService) {

    suspend fun getAllLots(body: RequestLotsBody): Response<ResponseLotsBody> {
        return auksionService.getAllLots(body)
    }

    suspend fun getLotDetails(body: PostDetailReq): Response<LotBean> {
        return auksionService.getDetails(body)
    }


}
