package com.example.auksion2.data.filter

data class FilterResponse(
    val regions: List<FilterResponseItems>,
    val areas: List<FilterResponseItems>,
    val groups: List<FilterResponseItems>,
    val categories: List<FilterResponseItems>,
    val directions: List<FilterResponseItems>,
    val result_code: Int,
    val result_msg: String
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