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
    val estadoCivil: Char = 'S'
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

    ////////////////
    var sumaUno = Suma(1,2);
    var sumaDos = Suma(1, null);
    var sumaTres = Suma(null, 1);
    sumaUno.sumar()
    sumaDos.sumar()
    Suma.pi
    Suma.historialSumas
    Suma.elevarAlCuadrado(2)


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

abstract class NumerosJava{
    protected val numeroUno: Int
    private val numeroDos: Int

    constructor(
        uno: Int,
        dos: Int
    ){//Bloque código constructor
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializado")
    }
}

abstract class Numeros( //Constructor Primario
    //uno: Int, //parámetro
    protected val numeroUno: Int, //propiedad de la clase protected
    protected val numeroDos: Int //propiedad de la clase protected
    ){
        init { //Bloque de código constructor PRIMARIO
            this.numeroUno
            numeroUno
            this.numeroDos
            numeroDos
            println("inicializado")
        }

}

class Suma(//constructor primario suma
    uno: Int, //parámetro
    dos: Int //parámetro
): Numeros(uno, dos){
    init{ //bloque constructor primario
        this.numeroUno
        this.numeroDos
    }
    constructor(//segundo constructor
        uno: Int?, //parametros
        dos: Int //parametro
    ):this( //llamada al constructor primario
        if(uno ==null) 0 else uno,
        dos
    ){} //Bloque de código del constructor
    constructor(//tercer constructor
        uno: Int, //parametros
        dos: Int? //parametro
    ):this( //llamada al constructor primario
        uno,
        if(dos ==null) 0 else uno
    ){}
    constructor(//cuarto constructor
        uno: Int?, //parametros
        dos: Int? //parametro
    ):this( //llamada al constructor primario
        if(uno ==null) 0 else uno,
        if(dos ==null) 0 else uno
    )
    public fun sumar():Int{
        return numeroUno+numeroDos
    }

    companion object{// atributos y métodos "compartidos" entre las instancias
        val pi=3.14
        fun elevarAlCuadrado(num:Int):Int{
            return num*num
        }
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma: Int){
            historialSumas.add(valorNuevaSuma)
        }

    }
}



