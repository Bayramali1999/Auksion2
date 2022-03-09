package com.example.auksion2.data.filter

data class FilterResponseItems(
    val soato: Int? = null,
    val name_ru: String? = null,
    val name_uk: String? = null,
    val name: String? = null,
    val id: Int? = null,
    val regions_id: Int? = null,
    val ordering: Int? = null,
    val confiscant_groups_id: Int? = null
)
//  "ordering": 3,
//            "name_ru": "Недвижимость",
//            "name_uk": "Кўчмас мулк",
//            "name": "Кўчмас мулк",
//            "id": 1
//"soato": 1726,
//"name_ru": "г.Ташкент",
//"name_uk": "Тошкент ш.",
//"name": "Тошкент ш.",
//"id": 1
//"regions_id": 8,
//"soato": "1714216",
//"name_ru": "Нарынский район",
//"name_uk": "Норин тумани",
//"name": "Норин тумани",
//"id": 111