import java.util.*
fun main(){
    println("hola")

    //tipos de variables

    //INMUTABLES (no reasignar)
    val inmutable: String = "Jenny";

    //MUTABLES (Re asignar)
    var mutable: String = "Lizeth"
    mutable = "Adrian"

    //val > var

    //Sintaxis Duck typing
    val ejemploVariable = "Ejemplo" //""-> string || ''->char
    val edadEjemplo: Int = 12
    ejemploVariable.trim()

    //variables primitivas
    val nombreProfesor: String = "Adrian"
    val sueldo: Double = 1.2
    val estadoCivil: char = 'S'
    val mayorEdad: Boolean = true
    //Clases JAVA
    val fechaNacimiento: Date = Date()

    if(true){

    }else{

    }

    //switch no existe
    val estadoCivilWhen = "S"
    when (estadoCivilWhen){
        ("S")->{
            println("acercarse")
        }
        "C" -> {
            println("alejarse")
        }
        "UN" -> println("hablar")
        else -> println("No reconocido")
    }
    val coqueteo = if (estadoCivilWhen == "S")"SI" else "NO"

}

//voidd == Unit
fun imprimitNombre(nombre:String): Unit {
    println("Nombre:${nombre}")
}

fun calcularSueldo(
    sueldo: Double, //Requerido
    tasa: Double = 12.00, //Opcional (Defecto)
    bonoEspecial: Double? = null, //Ocional (Null) -> nullable
):Double{
    //String ->String
    //Int -> Int?
    //Date -> Date?
    if (bonoEspecial ==null){
        return sueldo * (100/tasa)
    }else{
        return sueldo*(100/tasa)+bonoEspecial
    }
}




