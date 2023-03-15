package com.example.supermercado

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.supermercado.clases.Supermercado
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SupermercadoActivity : AppCompatActivity() {
    var arreglo: ArrayList<Supermercado> = arrayListOf()
    var idItemSeleccionado = 0
    var query: Query? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supermercado)
        val listSupermercado = findViewById<ListView>(R.id.list_supers)
        val adaptador: ArrayAdapter<Supermercado> = ArrayAdapter(
            this, //contexto
            android.R.layout.simple_list_item_1, //como se va a ver
            arreglo
        )
        listSupermercado.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonCrearSupermercado = findViewById<Button>(R.id.btn_interfaz_crear_supers)
        botonCrearSupermercado.setOnClickListener{
            val intent = Intent(this, CrearSupermercado::class.java)
            startActivity(intent)
        }
        val botonVerSupermercados = findViewById<Button>(R.id.btn_ver_supers)
        botonVerSupermercados.setOnClickListener{
            consultar(adaptador)
        }
        registerForContextMenu(listSupermercado)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //llamamos las opcuines del menú
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        //Obtener el id de ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var documento = arreglo.get(idItemSeleccionado).name.toString()
        return when (item.itemId){
            R.id.mi_editar  ->{
                val intent = Intent(this, ActualizarSupermercado::class.java)
                intent.putExtra("name", "${documento}")
                startActivity(intent)

                "${idItemSeleccionado}"
                return true
            }
            R.id.mi_eliminar ->{
                abrirDialogo()
                "${idItemSeleccionado}"
                return true
            }
            R.id.mis_productos ->{
                val intent = Intent(this, ProductoActivity::class.java)
                intent.putExtra("name", "${documento}")
                startActivity(intent)

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
            "Aceptar",
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
        var nombreDocumento = arreglo.get(idItemSelect).name.toString()
        val referencia = db
            .collection("supermarkets")
            .document("${nombreDocumento}")
            .delete()
            .addOnCompleteListener{/*si todo salio bien*/}
            .addOnFailureListener{/*Si algo salio mal*/}
    }

    fun consultar(
        adaptador: ArrayAdapter<Supermercado>
    ){
        val db = Firebase.firestore
        val supermercadoRefUnico = db
            .collection("supermarkets")
        limpiarArreglo()
        adaptador.notifyDataSetChanged()
        supermercadoRefUnico
            .get()
            .addOnSuccessListener {
                for(supermercado in it){
                    anadirAArregloSupermercado(arreglo, supermercado, adaptador)
                }
            }
    }
    fun anadirAArregloSupermercado(
        arregloNuevo: ArrayList<Supermercado>,
        supermercado: QueryDocumentSnapshot,
        adaptador:ArrayAdapter<Supermercado>
    ){
        val nuevoSuper = Supermercado(
            supermercado.data.get("name") as String?,
            supermercado.data.get("product") as Long?,
            supermercado.data.get("date") as String?,
            supermercado.data.get("accept") as String?,
            supermercado.data.get("hours") as Long?

        )
        arregloNuevo.add(nuevoSuper)
        adaptador.notifyDataSetChanged()
    }

    fun limpiarArreglo(){arreglo.clear()}
}