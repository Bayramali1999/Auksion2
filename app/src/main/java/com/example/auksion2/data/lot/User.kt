package com.example.auksion2.data.lot

data class User(
    val subject_type: Int? = null,
    val name: String? = null,
    val additional_phones: String? = null,
    val fio: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val inn: String? = null,
    val login: String? = null,
    val full_address: String? = null,
    val is_confirm_email: Int? = null,
    val is_confirm_phone: Int? = null,
    val offer_signed: Int? = null,
    val id: Int? = null
)