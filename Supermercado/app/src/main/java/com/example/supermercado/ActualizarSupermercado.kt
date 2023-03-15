package com.example.supermercado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.supermercado.clases.Supermercado
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ActualizarSupermercado : AppCompatActivity() {
    var arreglo: ArrayList<Supermercado> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_supermercado)

        val nombreDocumento = intent.getStringExtra("name").toString()

        val nombreSupermercado = findViewById<EditText>(R.id.input_nombre_super)
        val cantidadProductos= findViewById<EditText>(R.id.input_cantidad_productos)
        val fechaApertura = findViewById<EditText>(R.id.txt_date)
        val aceptaCheques = findViewById<EditText>(R.id.input_acepta_cheques)
        val horasAbierto = findViewById<EditText>(R.id.input_horas_abierto)

        val db = Firebase.firestore
        val supermercado = db
            .collection("supermarkets")
        supermercado
            .document("${nombreDocumento}")
            .get()
            .addOnSuccessListener {
                arreglo.add(
                    Supermercado(
                        it.data?.get("name") as String?,
                        it.data?.get("product") as Long?,
                        it.data?.get("date") as String?,
                        it.data?.get("accept") as String?,
                        it.data?.get("hours") as Long?
                    )
                )
                nombreSupermercado.setText(it.data?.get("name") as String?)
                cantidadProductos.setText((it.data?.get("product") as Long?).toString())
                fechaApertura.setText(it.data?.get("date") as String?)
                aceptaCheques.setText(it.data?.get("accept") as String?)
                horasAbierto.setText((it.data?.get("hours") as Long?).toString())
            }

        val botonActualizarSupermercado = findViewById<Button>(R.id.btn_actualizar_super)
        botonActualizarSupermercado.setOnClickListener{
            val nombreSupermercado = findViewById<EditText>(R.id.input_nombre_super).text.toString()
            val cantidadProductos= findViewById<EditText>(R.id.input_cantidad_productos).text.toString().toLong()
            val fechaApertura = findViewById<EditText>(R.id.txt_date).text.toString()
            val aceptaCheques = findViewById<EditText>(R.id.input_acepta_cheques).text.toString()
            val horasAbierto = findViewById<EditText>(R.id.input_horas_abierto).text.toString().toLong()
            actualizarSupermercado(nombreDocumento,nombreSupermercado,cantidadProductos,fechaApertura,aceptaCheques,horasAbierto)

        }

        val botonRegresarSupermercado = findViewById<Button>(R.id.btn_regresar)
        botonRegresarSupermercado.setOnClickListener{
            val intent = Intent(this, SupermercadoActivity::class.java)
            startActivity(intent)
        }
    }

    fun actualizarSupermercado(
        nombreDocument: String,
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
        supermercado.document("${nombreDocument}").set(datosSupermercado)

    }
}