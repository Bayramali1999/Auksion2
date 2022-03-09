package com.example.auksion2.common

import com.example.auksion2.api_service.ApiClient
import com.example.auksion2.api_service.AuksionService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Constant {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://mobile.e-auksion.uz/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val auctionService: AuksionService by lazy {
        retrofit.create(AuksionService::class.java)
    }

    val apiClient = ApiClient(auctionService)
}
