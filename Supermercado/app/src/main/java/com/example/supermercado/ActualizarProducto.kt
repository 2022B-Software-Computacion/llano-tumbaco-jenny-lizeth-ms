package com.example.supermercado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.supermercado.clases.Producto
import com.example.supermercado.clases.Supermercado
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ActualizarProducto : AppCompatActivity() {
    var arreglo: ArrayList<Producto> = arrayListOf()
    var nombreDocumentoSuper: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_producto)

        val nombreDocumento = intent.getStringExtra("nameProducto").toString()
        nombreDocumentoSuper = intent.getStringExtra("name").toString()

        val nombreProducto = findViewById<EditText>(R.id.input_act_nombre_producto)
        val cantidadProductos= findViewById<EditText>(R.id.input_act_num_productos)
        val fechaCaducidad = findViewById<EditText>(R.id.input_act_fecha_caducidad)
        val hechoEnEcuador = findViewById<EditText>(R.id.input_act_hecho_Ecuador)
        val precio = findViewById<EditText>(R.id.input_act_precio)

        val db = Firebase.firestore
        val producto = db
            .collection("supermarkets")
            .document("${nombreDocumentoSuper}")
            .collection("products")
        producto
            .document("${nombreDocumento}")
            .get()
            .addOnSuccessListener {
                arreglo.add(
                    Producto(
                        it.data?.get("name") as String?,
                        it.data?.get("numProduct") as Long?,
                        it.data?.get("dateC") as String?,
                        it.data?.get("doneEcuador") as String?,
                        it.data?.get("price") as Long?
                    )
                )

                nombreProducto.setText(it.data?.get("name") as String?)
                cantidadProductos.setText((it.data?.get("numProduct") as Long?).toString())
                fechaCaducidad.setText(it.data?.get("dateC") as String?)
                hechoEnEcuador.setText(it.data?.get("doneEcuador") as String?)
                precio.setText((it.data?.get("price") as Long?).toString())
            }

        //obtenerDocumento(nombreDocumento)
        //Toast.makeText(this, arreglo.get(0).name, Toast.LENGTH_LONG).show()


        val botonActualizarProducto = findViewById<Button>(R.id.btn_actualizar_producto)
        botonActualizarProducto.setOnClickListener{
            val nombreProducto = findViewById<EditText>(R.id.input_act_nombre_producto).text.toString()
            val cantidadProductos= findViewById<EditText>(R.id.input_act_num_productos).text.toString().toLong()
            val fechaCaducidad = findViewById<EditText>(R.id.input_act_fecha_caducidad).text.toString()
            val hechoEnEcuador = findViewById<EditText>(R.id.input_act_hecho_Ecuador).text.toString()
            val precio = findViewById<EditText>(R.id.input_act_precio).text.toString().toLong()
            actualizarSupermercado(nombreDocumento,nombreProducto,cantidadProductos,fechaCaducidad,hechoEnEcuador,precio)

        }

        val botonRegresarProducto = findViewById<Button>(R.id.btn_act_regresar_productos)
        botonRegresarProducto.setOnClickListener{
            val intent = Intent(this, ProductoActivity::class.java)
            intent.putExtra("name", "${nombreDocumentoSuper}")
            startActivity(intent)
        }
    }

    fun actualizarSupermercado(
        nombreDocument: String,
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
        producto.document("${nombreDocument}").set(datosProducto)

    }
}