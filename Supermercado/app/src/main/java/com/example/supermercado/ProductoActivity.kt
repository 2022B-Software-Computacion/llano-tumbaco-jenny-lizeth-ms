package com.example.supermercado

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.supermercado.clases.Producto
import com.example.supermercado.clases.Supermercado
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProductoActivity : AppCompatActivity() {
    var arreglo: ArrayList<Producto> = arrayListOf()
    var idItemSeleccionado = 0
    var query: Query? = null
    var nombreDocumentoSuper: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)
        nombreDocumentoSuper = intent.getStringExtra("name").toString()

        //editar el nombre del super que está en la interfaz supermercado
        val nombreSuper = findViewById<TextView>(R.id.txt_name_super)
        nombreSuper.setText(nombreDocumentoSuper)

        //lista de productos
        val listProducto = findViewById<ListView>(R.id.list_productos)
        val adaptador: ArrayAdapter<Producto> = ArrayAdapter(
            this, //contexto
            android.R.layout.simple_list_item_1, //como se va a ver
            arreglo
        )
        listProducto.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonCrearProducto = findViewById<Button>(R.id.btn_interfaz_crear_productos)
        botonCrearProducto.setOnClickListener{
            val intent = Intent(this, CrearProducto::class.java)
            intent.putExtra("name", "${nombreDocumentoSuper}")
            startActivity(intent)
        }
        val botonVerProductos = findViewById<Button>(R.id.btn_ver_productos)
        botonVerProductos.setOnClickListener{
            consultar(adaptador)
        }
        registerForContextMenu(listProducto)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //llamamos las opcuines del menú
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_producto, menu)
        //Obtener el id de ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var documento = arreglo.get(idItemSeleccionado).nameP.toString()
        return when (item.itemId){
            R.id.mi_editar  ->{
                val intent = Intent(this, ActualizarProducto::class.java)
                intent.putExtra("nameProducto", "${documento}")
                intent.putExtra("name", "${nombreDocumentoSuper}")
                startActivity(intent)

                "${idItemSeleccionado}"
                return true
            }
            R.id.mi_eliminar ->{
                abrirDialogo()
                "${idItemSeleccionado}"
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar")
        builder.setPositiveButton(
            "Accept",
            DialogInterface.OnClickListener{ dialog, which ->
                //al aceptar eliminar el registro
                eliminarRegistro(idItemSeleccionado)
            }
        )
        builder.setNegativeButton(
            "Cancelar",
            null
        )
        val dialogo = builder.create()
        dialogo.show()
    }

    fun eliminarRegistro(idItemSelect: Int){
        val db = Firebase.firestore
        var nombreDocumento = arreglo.get(idItemSelect).nameP.toString()
        val referencia = db
            .collection("supermarkets")
            .document("${nombreDocumentoSuper}")
            .collection("products")
                referencia
            .document("${nombreDocumento}")
            .delete()
            .addOnCompleteListener{/*si todo salio bien*/}
            .addOnFailureListener{/*Si algo salio mal*/}
    }

    fun consultar(
        adaptador: ArrayAdapter<Producto>
    ){
        val db = Firebase.firestore
        val producto = db
            .collection("supermarkets")
            .document("${nombreDocumentoSuper}")
            .collection("products")
        limpiarArreglo()
        adaptador.notifyDataSetChanged()
        producto
            .get()
            .addOnSuccessListener {
                for(supermercado in it){
                    anadirAArregloProducto(arreglo, supermercado, adaptador)
                }
            }
    }
    fun anadirAArregloProducto(
        arregloNuevo: ArrayList<Producto>,
        producto: QueryDocumentSnapshot,
        adaptador: ArrayAdapter<Producto>
    ){
        val nuevoProducto = Producto(
            producto.data.get("name") as String?,
            producto.data.get("numProduct") as Long?,
            producto.data.get("dateC") as String?,
            producto.data.get("doneEcuador") as String?,
            producto.data.get("price") as Long?

        )
        arregloNuevo.add(nuevoProducto)
        adaptador.notifyDataSetChanged()
    }

    fun limpiarArreglo(){arreglo.clear()}
}