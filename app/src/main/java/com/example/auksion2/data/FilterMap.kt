package com.example.auksion2.data

import android.os.Parcel
import android.os.Parcelable

data class FilterMap(
    var lot_number: String? = null,
    var confiscant_groups_id: Int? = null,
    var confiscant_categories_id: Int? = null,
    var regions_id: Int? = null,
    var areas_id: Int? = null,
    var orderby_: String? = null,
    var order_type: String? = null,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(lot_number)
        parcel.writeValue(confiscant_groups_id)
        parcel.writeValue(confiscant_categories_id)
        parcel.writeValue(regions_id)
        parcel.writeValue(areas_id)
        parcel.writeString(orderby_)
        parcel.writeString(order_type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FilterMap> {
        override fun createFromParcel(parcel: Parcel): FilterMap {
            return FilterMap(parcel)
        }

        override fun newArray(size: Int): Array<FilterMap?> {
            return arrayOfNulls(size)
        }
    }
}