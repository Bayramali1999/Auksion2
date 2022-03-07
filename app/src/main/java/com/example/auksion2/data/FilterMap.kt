package com.example.auksion2.data

data class FilterMap(
     val lot_number: String? = null,
     val confiscant_groups_id: Int? = null,
     val confiscant_categories_id: Int? = null,
     val regions_id: Int? = null,
     val areas_id: Int? = null,
     val orderby_: String? = null,
     val order_type: String? = null,
    )


//    "lot_nuber":"1268721",
//    "confiscant_groups_id":2,
//    "confiscant_categories_id":7,
//    "regions_id":8,
////    "areas_id":111
//"orderby_": "start_time",
//"order_type": "0"
