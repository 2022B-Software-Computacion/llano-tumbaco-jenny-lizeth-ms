package com.example.ltjlapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperEntrenador(
    contexto: Context?,
):SQLiteOpenHelper(
    contexto,
    "moviles", //nombre BDD
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaEntrenador =
            """
                CREATE TABLE ENTRENADOR(
                id INTEGER PRIMARY KEY AUTOINCREMENT, 
                nombre VARCHR(50),
                descripcion VARCHAR(50)
                )
        """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaEntrenador)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun crearEntrenador(
        nombre: String,
        descripcion: String
    ):Boolean{
        val baseDatosEscritura = writableDatabase
        val valorsAGuardar = ContentValues()
        valorsAGuardar.put("nombre", nombre)
        valorsAGuardar.put("descripcion", descripcion)
        val resultadoGuardar = baseDatosEscritura
            .insert(
                "ENTRENADOR", //NOMBRE DE TABLA
                null,
                valorsAGuardar //valores
            )
        baseDatosEscritura.close()
        return if(resultadoGuardar.toInt() == -1) false else true
    }

    fun eliminarEntrenador(id: Int): Boolean{
        //val conexionEscritra = this.writableDatabase
        val conexionEscritura = writableDatabase
        // "SELECT * FROM ENTRENADOR WHERE ID =?"
        //arrayOf(
        //  id.toString()
        // )
        val resultadoEliminacion = conexionEscritura
            .delete(
                "ENTRENADOR", //nombre tabla
            "id=?",//consulta where
                arrayOf(
                    id.toString()
                )//Parametros
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarEntrenadorFormulario(
        nombre:String,
        descripcion: String,
        idActualizar:Int
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresActualizar = ContentValues()
        valoresActualizar.put("nombre", nombre)
        valoresActualizar.put("descripcion", descripcion)
        val resultadoActualizacion = conexionEscritura
            .update(
                "Entrenador",
                valoresActualizar,
                "id=?",
                arrayOf(
                    idActualizar.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1)false else true
    }

    fun consultarEntrenadorPorId(id: Int):BEntrenador{
        //val baseDatosLecutra = this.readableDatabase
        val baseDatosLectura = readableDatabase
        val scriptConsultarUsuario = "SELECT * FROM ENTRENADOR WHERE ID =?"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario, //Consulta
            arrayOf(
                id.toString()
            )
        )
        //Logica busqueda
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        val usuarioEncontrado = BEntrenador(0,"","")
        val arreglo = arrayListOf<BEntrenador>()
        if(existeUsuario){
            do{
                val id = resultadoConsultaLectura.getInt(0)//columna indice 0->id
                val nombre = resultadoConsultaLectura.getString(1) //Columna indice 1->Nombre
                val descripcion  = resultadoConsultaLectura.getString(2) //columna indice 2->descripción
                if(id != null){
                    usuarioEncontrado.id = id
                    usuarioEncontrado.nombre = nombre
                    usuarioEncontrado.descripcion = descripcion
                    arreglo.add(usuarioEncontrado)
                }
            }while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado
    }
}

