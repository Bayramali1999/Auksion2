package com.example.auksion2.data.filter

data class FilterResponse(
    val regions: MutableList<FilterResponseItems>,
    val areas: MutableList<FilterResponseItems>,
    val groups: MutableList<FilterResponseItems>,
    val categories: MutableList<FilterResponseItems>,
    val directions: MutableList<FilterResponseItems>,
    val result_code: Int,
    val result_msg: String
)
