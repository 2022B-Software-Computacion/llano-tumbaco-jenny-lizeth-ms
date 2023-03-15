package com.example.supermercado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearProducto : AppCompatActivity() {
    var nombreDocumentoSuper: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_producto)

        nombreDocumentoSuper = intent.getStringExtra("name").toString()
        Toast.makeText(this,nombreDocumentoSuper, Toast.LENGTH_LONG).show()

        val botonCrearProducto=findViewById<Button>(R.id.btn_crear_producto)
        botonCrearProducto.setOnClickListener{
            val nombreProducto = findViewById<EditText>(R.id.input_nombre_producto).text.toString()
            val cantidadProductos= findViewById<EditText>(R.id.input_num_productos).text.toString().toLong()
            val fechaCaducidad = findViewById<EditText>(R.id.input_fecha_caducidad).text.toString()
            val hechoEnEcuador = findViewById<EditText>(R.id.input_hecho_Ecuador).text.toString()
            val precio = findViewById<EditText>(R.id.input_precio).text.toString().toLong()
            crearProducto(nombreProducto,cantidadProductos,fechaCaducidad,hechoEnEcuador,precio)
        }
        val botonRegresarProductos = findViewById<Button>(R.id.btn_regresar_productos)
        botonRegresarProductos.setOnClickListener{
            val intent = Intent(this, ProductoActivity::class.java)
            intent.putExtra("name", "${nombreDocumentoSuper}")
            startActivity(intent)
        }
    }
    fun crearProducto(
        nombre:String,
        cantidadProductos:Long,
        fechaCaducidad: String,
        hechoEnEcuador: String,
        precio: Long
    ){
        val db = Firebase.firestore
        val producto = db
            .collection("supermarkets")
            .document("${nombreDocumentoSuper}")
            .collection("products")
        val datosProducto = hashMapOf(
            "name" to nombre,
            "numProduct" to cantidadProductos,
            "dateC" to fechaCaducidad,
            "doneEcuador" to hechoEnEcuador,
            "price" to precio
        )
        producto.document("${nombre}").set(datosProducto)
            .addOnCompleteListener{/*si todo salio bien*/}
            .addOnFailureListener{/*Si algo salio mal*/}

    }
}