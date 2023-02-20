package com.example.recyclerviewlibro.clases
import android.os.Parcel
import android.os.Parcelable

class Lecturas (
    var id: Int,
    var nombre: String?,
    var autor: String?,
    var imagen: Int,
    var resumen: String?,
    var libros: ArrayList<Libro>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        arrayListOf<Libro>().apply {
            parcel.readArrayList(Libro::class.java.classLoader)
        }
    ) {
    }

    override fun toString():String{
        return "${nombre} - ${autor}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeString(autor)
        parcel.writeInt(imagen)
        parcel.writeString(resumen)
        parcel.writeList(libros)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Lecturas> {
        override fun createFromParcel(parcel: Parcel): Lecturas {
            return Lecturas(parcel)
        }

        override fun newArray(size: Int): Array<Lecturas?> {
            return arrayOfNulls(size)
        }
    }
}