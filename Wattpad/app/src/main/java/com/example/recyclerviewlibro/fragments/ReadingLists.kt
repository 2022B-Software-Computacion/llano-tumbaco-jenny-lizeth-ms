package com.example.recyclerviewlibro.fragments

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewlibro.R
import com.example.recyclerviewlibro.adaptador.LecturasAdapter
import com.example.recyclerviewlibro.clases.Lecturas
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.recyclerviewlibro.clases.Libro

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ReadingLists : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?{
        val vista = inflater.inflate(R.layout.fragment_reading_lists, container, false)
        val listaLibro = arrayListOf<Libro>()
        listaLibro
            .add(Libro(1,"!!Simplemente un contrato¡¡","450k","12.4M","43","Quiero agradecer a cada una de ustedes porque a pesar de malos comentarios ustedes comentaron pequelas ccosas",R.drawable.simplemente_un_contrato))

        //definir lista
        val listaLectura = arrayListOf<Lecturas>()
        listaLectura
            .add(Lecturas(1,"Lista de lectura de Terror","2 stories",R.drawable.terror,"Quiero agradecer a cada una de ustedes porque a pesar de malos comentarios ustedes comentaron pequelas ccosas",listaLibro))
        listaLectura
            .add(Lecturas(2,"Lista de lectura de Amor","7 stories",R.drawable.amor, "Quiero agradecer a cada una de ustedes porque a pesar de malos comentarios ustedes comentaron pequelas ccosas",listaLibro))
        listaLectura
            .add(Lecturas(3,"Lista de lectura de Tragedia", "4 stories",R.drawable.tragedia,"a",listaLibro) )
        listaLectura
            .add(Lecturas(4,"Lista de lectura de Suspenso","8 stories",R.drawable.suspenso ,"Quiero agradecer a cada una de ustedes porque a pesar de malos comentarios ustedes comentaron pequelas ccosas",listaLibro))
        listaLectura
            .add(Lecturas(5,"Lista de lectura de aventura","3 stories",R.drawable.aventura ,"Quiero agradecer a cada una de ustedes porque a pesar de malos comentarios ustedes comentaron pequelas ccosas",listaLibro))
        listaLectura
            .add(Lecturas(6,"Lista de lectura de ciencia ficción", "4 stories",R.drawable.ciencia_ficcion,"a",listaLibro) )
        listaLectura
            .add(Lecturas(7,"Lista de lectura de niños","1 story",R.drawable.infantil, "Quiero agradecer a cada una de ustedes porque a pesar de malos comentarios ustedes comentaron pequelas ccosas",listaLibro))

        //inicializar Recycle View
        val recyclerView =  vista.findViewById<RecyclerView>(R.id.rv_lista_lectura)
        inicializarRecyclerView(listaLectura, recyclerView)
        return vista
    }
    fun cambiar(view: View, savedInstanceState: Bundle?) {
        /*super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btn_p).setOnClickListener {
            findNavController().navigate(R.id.action_ReadingFragment_to_LibrosFragment)
        }*/

    }

    fun inicializarRecyclerView(
        lista:ArrayList<Lecturas>,
        recyclerView: RecyclerView
    ){
        val adaptador =LecturasAdapter(
            this, // Contexto
            lista, // Arreglo datos
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)

    }

/*
    override fun onItemClick(libros: ArrayList<Libro>?) {
        val intent = Intent(this, LibrosRecyclerView::class.java)
    }
*/

}