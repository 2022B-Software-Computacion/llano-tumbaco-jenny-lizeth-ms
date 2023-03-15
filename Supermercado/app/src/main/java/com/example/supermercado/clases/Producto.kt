package com.example.supermercado.clases

class Producto(
    public var nameP:String?,
    public var numProduct:Long?,
    public var dateC: String?,
    public var doneEcuador: String?,
    public var price: Long?
){
    override fun toString():String{
        return "${nameP} - Precio:${price} - Fecha de caducidad:${dateC} - Num. Productos: ${numProduct}-Hecho en Ecuador:${doneEcuador}"
    }
}