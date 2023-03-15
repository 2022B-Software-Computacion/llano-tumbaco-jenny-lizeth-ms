package com.example.supermercado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearSupermercado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_supermercado)

        val botonCrearSupermercado=findViewById<Button>(R.id.btn_crear_super)
        botonCrearSupermercado.setOnClickListener{
            val nombreSupermercado = findViewById<EditText>(R.id.input_nombre_super).text.toString()
            val cantidadProductos= findViewById<EditText>(R.id.input_cantidad_productos).text.toString().toLong()
            val fechaApertura = findViewById<EditText>(R.id.txt_date).text.toString()
            val aceptaCheques = findViewById<EditText>(R.id.input_acepta_cheques).text.toString()
            val horasAbierto = findViewById<EditText>(R.id.input_horas_abierto).text.toString().toLong()
            crearSupermercado(nombreSupermercado,cantidadProductos,fechaApertura,aceptaCheques,horasAbierto)
        }
        val botonRegresarSupermercado = findViewById<Button>(R.id.btn_regresar)
        botonRegresarSupermercado.setOnClickListener{
            val intent = Intent(this, SupermercadoActivity::class.java)
            startActivity(intent)
        }
    }
    fun crearSupermercado(
        nombre:String,
        cantidadProductos:Long,
        fechaApertura: String,
        aceptaCheques: String,
        horasAbierto: Long
    ){
        val db = Firebase.firestore
        val supermercado = db
            .collection("supermarkets")
        val datosSupermercado = hashMapOf(
            "name" to nombre,
            "product" to cantidadProductos,
            "date" to fechaApertura,
            "accept" to aceptaCheques,
            "hours" to horasAbierto
        )
        supermercado.document("${nombre}").set(datosSupermercado)

    }
}