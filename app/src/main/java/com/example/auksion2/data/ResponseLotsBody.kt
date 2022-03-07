package com.example.auksion2.data

data class ResponseLotsBody(
    val shortLotBeans: MutableList<ShortLotBeans>,
    val result_msg: String,
    val result_code: Int
)