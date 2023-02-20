package com.example.recyclerviewlibro.clases

import android.os.Parcel
import android.os.Parcelable

class Libro (
    var id: Int,
    var nombre: String?,
    var autor: String?,
    var avance: String?,
    var resumen: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun toString():String{
        return "${nombre} - ${autor} - ${avance}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeString(autor)
        parcel.writeString(avance)
        parcel.writeString(resumen)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Libro> {
        override fun createFromParcel(parcel: Parcel): Libro {
            return Libro(parcel)
        }

        override fun newArray(size: Int): Array<Libro?> {
            return arrayOfNulls(size)
        }
    }
}