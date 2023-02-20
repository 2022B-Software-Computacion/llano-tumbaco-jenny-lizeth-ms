package com.example.recyclerviewlibro.clases

import android.os.Parcel
import android.os.Parcelable

class Libro (
    var id: Int,
    var nombre: String?,
    var estrella: String?,
    var vista: String?,
    var libros: String?,
    var resumen: String?,
    var imgBook: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun toString():String{
        return "${nombre} "
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeString(estrella)
        parcel.writeString(vista)
        parcel.writeString(libros)
        parcel.writeString(resumen)
        parcel.writeInt(imgBook)
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