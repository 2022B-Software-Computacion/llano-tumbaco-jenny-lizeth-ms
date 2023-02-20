package com.example.recyclerviewlibro.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewlibro.R
import com.example.recyclerviewlibro.adaptador.RecyclerViewAdaptadorLibro
import com.example.recyclerviewlibro.clases.Libro
import android.view.LayoutInflater

class LibrosRecyclerView : AppCompatActivity() {

    override fun onCreate(LayoutInflater inflater, ViewGroup container,
                          Bundle savedInstanceState){
    View vista=inflater.inflate(R.layout.ac, container, false)
    //definir lista
        val listaLibro = arrayListOf<Libro>()
        listaLibro
            .add(Libro(1,"!!Simplemente un contrato¡¡","LauraLopez261","Completado","Quiero agradecer a cada una de ustedes porque a pesar de malos comentarios ustedes comentaron pequelas ccosas"))
        listaLibro
            .add(Libro(2,"Seis Segundos","Solano_T","Completed", "Quiero agradecer a cada una de ustedes porque a pesar de malos comentarios ustedes comentaron pequelas ccosas"))

        //inicializar Recycle View
        val recyclerView = findViewById<RecyclerView>(R.id.rv_libros)

        inicializarRecyclerView(listaLibro, recyclerView)

    }

    fun inicializarRecyclerView(
        lista:ArrayList<Libro>,
        recyclerView: RecyclerView
    ){
        val adaptador = RecyclerViewAdaptadorLibro(
            this, // Contexto
            lista, // Arreglo datos
            recyclerView // Recycler view
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged() //modificar el arreglo
    }

}