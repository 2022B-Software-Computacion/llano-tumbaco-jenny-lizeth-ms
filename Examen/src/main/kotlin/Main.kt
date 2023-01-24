import java.io.File
import java.io.InputStream
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*


fun main() {
    do {
        println("*********************Menú*********************")
        println("---------Supermercado------------")
        println("1. Crear")
        println("2. Mostrar Supermercados con productos")
        println("3. Actualizar")
        println("4. Gestión de los Productos")
        println("5. Borrar Supermercado con productos")
        println("6. Salir")

        val opcion = readln().toInt()
        var listaSupermercados = leerArchivo()

        when (opcion) {
            1 -> {//Crear
                val miSupermercado = Supermercado()
                miSupermercado.crearSupermercado()
                listaSupermercados.add(miSupermercado)
                escribirArchivo(listaSupermercados)
            }
            2->{//Mostrar
                listaSupermercados.forEach{
                    supermercado: Supermercado ->
                    println("\n****Supermercado****")
                    println("nombre:" + supermercado.nombre + "   #Productos:" + supermercado.cantidadProductos+"   Fecha de apertura:"+supermercado.fechaApertura+"   Acepta cheques:"+supermercado.aceptaCheques+"  Horas abierto:"+supermercado.horasAlDiaAbierto)
                    println("***+Productos****")
                    supermercado.listaProductos.forEach{
                        producto: Producto ->
                        println("nombre:" + producto.nombre+"   #Productos:"+producto.productosExistentes+"   Fecha de caducidad:"+producto.fechaCaducidad+"   Hecho en Ecuador:"+producto.estaHechoEnEcuador+"   Precio:"+producto.precio)
                    }
                }
            }
            3->{//actualizar
                println("Nombre del supermercado: ")
                val nombreSuper = readln()
                var auxSuperm = Supermercado()
                listaSupermercados = auxSuperm.actualizarSupermercado(listaSupermercados, nombreSuper)
                escribirArchivo(listaSupermercados)
                println("¡Información actualizada con EXITO!\n")
            }

            4->{//CRUD del producto
                println("Nombre del supermercado: ")
                val nombreSuper = readln()
                var superProducto = Supermercado()
                val indiceSuper = superProducto.buscarSupermercado(listaSupermercados, nombreSuper)

                do{
                    println("\n----------Producto------------")
                    println("7. Crear")
                    println("8. Mostrar productos")
                    println("9. Actualizar")
                    println("10. Borrar")
                    println("11. Salir")
                    val opcionP = readln().toInt()

                    listaSupermercados = leerArchivo()

                    when(opcionP){
                        7->{ //crear
                            val miProducto = Producto()
                            miProducto.crearProducto()
                            listaSupermercados[indiceSuper].listaProductos.add(miProducto)
                            escribirArchivo(listaSupermercados)
                        }
                        8->{ //mostrar
                            println("***+Productos****")
                            var productos = listaSupermercados[indiceSuper].listaProductos
                            productos.forEach{
                                p: Producto ->
                                println("nombre:" + p.nombre+"   #Productos:"+p.productosExistentes+"   Fecha de caducidad:"+p.fechaCaducidad+"   Hecho en Ecuador:"+p.estaHechoEnEcuador+"   Precio:"+p.precio)
                            }
                        }
                        9->{//actualizar
                            println("Nombre del producto: ")
                            val nombreProd = readln()
                            var auxProd = Producto()
                            listaSupermercados[indiceSuper].listaProductos = auxProd.actualizarProducto(listaSupermercados[indiceSuper].listaProductos, nombreProd)
                            escribirArchivo(listaSupermercados)
                            println("¡Información actualizada con EXITO!\n")
                        }
                        10->{//Borrar
                            println("Nombre del producto: ")
                            val nombreProd = readln()
                            var auxProd = Producto()
                            listaSupermercados[indiceSuper].listaProductos = auxProd.borrarProducto(listaSupermercados[indiceSuper].listaProductos, nombreProd)
                            escribirArchivo(listaSupermercados)
                            println("¡Producto eliminado con EXITO!\n")
                        }
                    }

                }while (opcionP!=11)
            }
            5->{
                println("Nombre del supermercado: ")
                val nombreSuper = readln()
                var auxSuperm = Supermercado()
                listaSupermercados = auxSuperm.borrarSupermercado(listaSupermercados, nombreSuper)
                escribirArchivo(listaSupermercados)
                println("¡Supermercado eliminado con EXITO!\n")
            }

        }
    } while (opcion != 6)
}

fun escribirArchivo(texto:ArrayList<Supermercado>){

    val archivo = File("archivos/texto.txt")
    archivo.writeText("")
    texto.forEach { superActual: Supermercado ->
        archivo.appendText("Supermercado: ")
        archivo.appendText("nombre:" + superActual.nombre + " :#Productos:" + superActual.cantidadProductos+" :Fecha de apertura:"+superActual.fechaApertura+" :Acepta cheques:"+superActual.aceptaCheques+" :Horas abierto:"+superActual.horasAlDiaAbierto+"\n")

        superActual.listaProductos.forEach { producto: Producto ->
            archivo.appendText("Productos: ")
            archivo.appendText("nombre:" + producto.nombre+" :#Productos:"+producto.productosExistentes+" :Fecha de caducidad:"+producto.fechaCaducidad+" :Hecho en Ecuador:"+producto.estaHechoEnEcuador+" :Precio:"+producto.precio+"\n")
        }
    }
}

fun leerArchivo():ArrayList<Supermercado>{
    val inputStream: InputStream = File("archivos/texto.txt").inputStream()
    val lineas = mutableListOf<String>()
    inputStream.bufferedReader().useLines { lines -> lines.forEach { lineas.add(it) } }

    var listaSupermercados = ArrayList<Supermercado>()
    var indiceSuper = 0

    lineas.forEach{ linea: String->
        val tokens = listOf(*linea.split("\\s*:\\s*".toRegex()).toTypedArray())

        if(tokens[0].equals("Supermercado")){
            val s = Supermercado()
            s.nombre = tokens[2]
            s.cantidadProductos = tokens[4].toInt()
            s.fechaApertura = LocalDate.parse(tokens[6])
            s.aceptaCheques = tokens[8].toBoolean()
            s.horasAlDiaAbierto = tokens[10].toDouble()
            listaSupermercados.add(s)
            indiceSuper = listaSupermercados.indexOf(s)
        }else{
            if(tokens[0].equals("Productos") ){
                val p = Producto()
                p.nombre = tokens[2]
                p.productosExistentes = tokens[4].toInt()
                p.fechaCaducidad = LocalDate.parse(tokens[6])
                p.estaHechoEnEcuador = tokens[8].toBoolean()
                p.precio = tokens[10].toDouble()
                listaSupermercados[indiceSuper].listaProductos.add(p)
            }
        }
    }
    return listaSupermercados

}
class Supermercado{
    var nombre:String = ""
    var cantidadProductos: Int = 0
    var fechaApertura = LocalDate.parse("2022-12-12")
    var aceptaCheques: Boolean = false
    var horasAlDiaAbierto: Double = 9.5
    var listaProductos = arrayListOf<Producto>()

    fun crearSupermercado() {
        println("Ingrese el nombre del supermercado: ")
        val n = readln()
        this.nombre = n
        println("Ingrese la cantidad de productos del supermercado: ")
        val c = readln().toInt()
        this.cantidadProductos = c
        println("Ingresa la fecha de fundación del supermercado (aa-mm-dd): ")
        val f = LocalDate.parse(readln())
        this.fechaApertura = f
        println("¿El supermercado acepta cheques?: Si  o  No")
        val b = readln()
        if(b.equals("Si")){
            this.aceptaCheques = true
        }else {
            (b.equals("No"))
                this.aceptaCheques = false
        }
        println("Ingresa las horas que el supermercado está abierto: ")
        val h = readln().toDouble()
        this.horasAlDiaAbierto = h

    }

    fun buscarSupermercado(listaSuper:ArrayList<Supermercado>, nombreSuper:String):Int{
        var indice = 0
        listaSuper.forEach{
            supermercado:Supermercado ->
            if(supermercado.nombre.equals(nombreSuper)){
                indice =  listaSuper.indexOf(supermercado)
            }
        }
        return indice
    }

    fun actualizarSupermercado(listaSupers:ArrayList<Supermercado>, nombreSuper:String):ArrayList<Supermercado>{
        var s = Supermercado()
        val indiceSuper = s.buscarSupermercado(listaSupers, nombreSuper)
        s = listaSupers[indiceSuper]
        val info: MutableMap<String, String> = mutableMapOf("nombre" to s.nombre,
            "#Productos" to s.cantidadProductos.toString(), "Fecha de apertura" to s.fechaApertura.toString(),
            "Aceptación de cheques" to s.aceptaCheques.toString(), " Horas de apertura" to s.horasAlDiaAbierto.toString())

        println("¿Qué información quieres cambiar?: ")
        var auxCont = 0
        info.forEach{
                (k, v) ->
            auxCont = auxCont+1
            println("   $auxCont. $k : $v")
        }
        val eleccion = readln().toInt()

        println("Escribe el nuevo valor: ")
        val newInfo = readln()
        when(eleccion){
            1->{listaSupers[indiceSuper].nombre=newInfo}
            2->{listaSupers[indiceSuper].cantidadProductos=newInfo.toInt()}
            3->{listaSupers[indiceSuper].fechaApertura=LocalDate.parse(newInfo)}//LocalDate.parse(newInfo, DateTimeFormatter.ISO_DATE))}
            4->{if(newInfo.equals("Si")){
                listaSupers[indiceSuper].aceptaCheques = true
            }else {
                (newInfo.equals("No"))
                listaSupers[indiceSuper].aceptaCheques = false
            }}
            5->{listaSupers[indiceSuper].horasAlDiaAbierto=newInfo.toDouble()}
        }
        return listaSupers
    }

    fun borrarSupermercado(listaSupers:ArrayList<Supermercado>, nombreSuper:String):ArrayList<Supermercado>{
        var s = Supermercado()
        val indiceSuper = s.buscarSupermercado(listaSupers, nombreSuper)
        listaSupers.removeAt(indiceSuper)

        return listaSupers
    }
}

class Producto{
    var nombre:String = ""
    var productosExistentes: Int = 0
    var fechaCaducidad = LocalDate.parse("2022-12-12")
    var estaHechoEnEcuador: Boolean = false
    var precio: Double = 0.0

    fun crearProducto(){
        println("Ingrese el nombre del producto: ")
        val n = readln()
        this.nombre = n
        println("Ingrese la cantidad de productos existente: ")
        val c = readln().toInt()
        this.productosExistentes = c
        println("Ingresa la fecha decaducidad (aa-mm-dd): ")
        val f = LocalDate.parse(readln())
        this.fechaCaducidad = f
        println("¿El prducto está hecho en Ecuador?: Si  o  No")
        val b = readln()
        if(b.equals("Si")){
            this.estaHechoEnEcuador = true
        }else {
            (b.equals("No"))
            this.estaHechoEnEcuador = false
        }
        println("Ingresa el precio: ")
        val h = readln().toDouble()
        this.precio = h
    }
    fun buscarProducto(listaSuper:ArrayList<Producto>, nombreProdu:String):Int{
        var indice = 0
        listaSuper.forEach{
                producto:Producto ->
            if(producto.nombre.equals(nombreProdu)){
                indice =  listaSuper.indexOf(producto)
            }
        }
        return indice
    }

    fun actualizarProducto(listaProd:ArrayList<Producto>, nombreProd:String):ArrayList<Producto>{
        var p = Producto()
        val indiceProd = p.buscarProducto(listaProd, nombreProd)
        p = listaProd[indiceProd]
        val info: MutableMap<String, String> = mutableMapOf("nombre" to p.nombre,
            "#Productos" to p.productosExistentes.toString(), "Fecha de caducidad" to p.fechaCaducidad.toString(),
            "Hecho en Ecuador" to p.estaHechoEnEcuador.toString(), "Precio" to p.precio.toString())

        println("¿Qué información quieres cambiar?: ")
        var auxCont = 0
        info.forEach{
                (k, v) ->
            auxCont = auxCont+1
            println("   $auxCont. $k : $v")
        }
        val eleccion = readln().toInt()

        println("Escribe el nuevo valor: ")
        val newInfo = readln()
        when(eleccion){
            1->{listaProd[indiceProd].nombre=newInfo}
            2->{listaProd[indiceProd].productosExistentes=newInfo.toInt()}
            3->{listaProd[indiceProd].fechaCaducidad=LocalDate.parse(newInfo)}
            4->{listaProd[indiceProd].estaHechoEnEcuador=newInfo.toBoolean()}
            5->{listaProd[indiceProd].precio=newInfo.toDouble()}
        }
        return listaProd
    }
    fun borrarProducto(listaProd:ArrayList<Producto>, nombreProd:String):ArrayList<Producto>{
        var p = Producto()
        val indiceProd = p.buscarProducto(listaProd, nombreProd)
        listaProd.removeAt(indiceProd)

        return listaProd
    }

}