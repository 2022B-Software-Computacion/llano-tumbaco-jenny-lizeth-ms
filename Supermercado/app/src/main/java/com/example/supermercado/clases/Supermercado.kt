package com.example.supermercado.clases

class Supermercado(
    public var name:String?,
    public var product:Long?,
    public var date: String?,
    public var accept: String?,
    public var hours: Long?
){
    override fun toString():String{
        return "${name} - ${accept} acepta cheques - ${product} productos - Horas abierto: ${hours} - Fecha de inauguración: ${date}"
    }
}