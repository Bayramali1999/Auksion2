package com.example.auksion2.data

import android.os.Parcel
import android.os.Parcelable

data class RequestLotsBody(
    val action: Int,
    val version: String,
    val language: String,
    val currentPage: String? = null,
    val is_gzipped: Int = 0,
    val filters_map: FilterMap? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString(),
        parcel.readInt(),
        parcel.readParcelable(FilterMap::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(action)
        parcel.writeString(version)
        parcel.writeString(language)
        parcel.writeString(currentPage)
        parcel.writeInt(is_gzipped)
        parcel.writeParcelable(filters_map, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RequestLotsBody> {
        override fun createFromParcel(parcel: Parcel): RequestLotsBody {
            return RequestLotsBody(parcel)
        }

        override fun newArray(size: Int): Array<RequestLotsBody?> {
            return arrayOfNulls(size)
        }
    }
}

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
