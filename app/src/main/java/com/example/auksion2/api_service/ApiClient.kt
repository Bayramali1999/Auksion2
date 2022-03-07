package com.example.auksion2.api_service

import com.example.auksion2.data.RequestLotsBody
import com.example.auksion2.data.ResponseLotsBody
import retrofit2.Response

class ApiClient(private val auksionService: AuksionService) {

    suspend fun getAllLots(body: RequestLotsBody): Response<ResponseLotsBody> {
        return auksionService.getAllLots(body)
    }

}
