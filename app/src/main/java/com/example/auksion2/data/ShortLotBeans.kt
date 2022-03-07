package com.example.auksion2.data

data class ShortLotBeans(
    val name: String,
    val lot_statuses_id: Int,
    val lot_number: String,
    val zaklad_summa: Double? = null,
    val start_price: Double? = null,
    val confiscant_groups_id: Int,
    val confiscant_categories_id: Int,
    val confiscants_id: Int,
    val order_end_time_str: String,
    val start_time_date_str: String,
    val file_hash: String,
    val id: Int,
)
//
//"name": "Темирчилик устахонаси",
//"lot_statuses_id": 2,
//"lot_number": "1395606",
//"zaklad_summa": 4725832.45,
//"start_price": 9.4516649E7,
//"confiscant_groups_id": 1,
//"confiscant_categories_id": 1,
//"confiscants_id": 370,
//"order_end_time_str": "10.03.2022 09:00",
//"start_time_date_str": "10.03.2022 10:00",
//"file_hash": "37f05994464b140c59ab29207f3b8d94e42582dd",
//"id": 1395606