package com.example.auksion2.data

data class RequestLotsBody(
    val action: Int,
    val version: String,
    val language: String,
    val currentPage: String?=null,
    val is_gzipped: Int = 0,
    val filters_map: FilterMap? = null
)

//    {
//        "action":5,
//        "version":"1.3.7",
//        "language":"uz",
//        "currentPage":"1" ,
//        "is_gzipped": 0
//    }
//{ "action":7,
//    "version":"1.3.7",
//    "language":"uz",
//    "is_gzipped": 0
//}
