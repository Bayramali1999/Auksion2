package com.example.auksion2.data.lot

data class PostDetailReq(
     val action: Int,
     val version: String? = null,
     val language: String? = null,
     val lot_id: String? = null,
     val is_view: String? = null,
     val buyers_id: String? = null,
     val is_gzipped: Int = 0
)