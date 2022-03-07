package com.example.auksion2.data

import android.os.Parcel
import android.os.Parcelable

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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(lot_statuses_id)
        parcel.writeString(lot_number)
        parcel.writeValue(zaklad_summa)
        parcel.writeValue(start_price)
        parcel.writeInt(confiscant_groups_id)
        parcel.writeInt(confiscant_categories_id)
        parcel.writeInt(confiscants_id)
        parcel.writeString(order_end_time_str)
        parcel.writeString(start_time_date_str)
        parcel.writeString(file_hash)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ShortLotBeans> {
        override fun createFromParcel(parcel: Parcel): ShortLotBeans {
            return ShortLotBeans(parcel)
        }

        override fun newArray(size: Int): Array<ShortLotBeans?> {
            return arrayOfNulls(size)
        }
    }
}
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