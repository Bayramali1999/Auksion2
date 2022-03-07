package com.example.auksion2.api_service

import com.example.auksion2.data.RequestLotsBody
import com.example.auksion2.data.ResponseLotsBody
import com.example.auksion2.data.lot.LotBean
import com.example.auksion2.data.lot.PostDetailReq
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.HTTP

interface AuksionService {

    @HTTP(path = "api/v1/mobile", hasBody = true, method = "POST")
    suspend fun getAllLots(@Body body: RequestLotsBody): Response<ResponseLotsBody>


    @HTTP(path = "api/v1/mobile", hasBody = true, method = "POST")
    suspend fun getDetails(@Body ew: PostDetailReq?): Response<LotBean>

}
