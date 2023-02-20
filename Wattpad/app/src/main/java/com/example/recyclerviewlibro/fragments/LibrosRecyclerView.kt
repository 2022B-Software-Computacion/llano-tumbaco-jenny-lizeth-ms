package com.example.recyclerviewlibro.fragments

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewlibro.R
import com.example.recyclerviewlibro.adaptador.RecyclerViewAdaptadorLibro
import com.example.recyclerviewlibro.clases.Libro
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class LibrosRecyclerView : Fragment()  {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                          savedInstanceState: Bundle?): View?{
        val vista = inflater.inflate(R.layout.fragment_libro, container, false)
        //definir lista
        val listaLibro = arrayListOf<Libro>()
        listaLibro
            .add(Libro(1,"!!Simplemente un contrato¡¡","450K","12.4M","43","Quiero agradecer a cada una de ustedes porque a pesar de malos comentarios ustedes comentaron pequelas ccosas",R.drawable.simplemente_un_contrato))
        listaLibro
            .add(Libro(2,"El elevador de Central Park","40K","15.4M","50","Quiero agradecer a cada una de ustedes porque a pesar de malos comentarios ustedes comentaron pequelas ccosas",R.drawable.elevador))
        listaLibro
            .add(Libro(3,"Kate & Ethan | En físico el 12 de abril","50K","1M","20","Quiero agradecer a cada una de ustedes porque a pesar de malos comentarios ustedes comentaron pequelas ccosas",R.drawable.kate_ethan))
        listaLibro
            .add(Libro(4,"Si me dices que no (GRATIS)","55K","8M","80","Quiero agradecer a cada una de ustedes porque a pesar de malos comentarios ustedes comentaron pequelas ccosas",R.drawable.me_dice_no))
        listaLibro
            .add(Libro(5,"HENKO","38K","12.4M","56","Quiero agradecer a cada una de ustedes porque a pesar de malos comentarios ustedes comentaron pequelas ccosas",R.drawable.henko))
        listaLibro
            .add(Libro(6,"Refugio de amor","20K","24M","34","Quiero agradecer a cada una de ustedes porque a pesar de malos comentarios ustedes comentaron pequelas ccosas",R.drawable.refugio))
        listaLibro
            .add(Libro(7,"Soy una ardilla en el Apocalipsis","20K","1.4k","18","Quiero agradecer a cada una de ustedes porque a pesar de malos comentarios ustedes comentaron pequelas ccosas",R.drawable.ardilla))

        //inicializar Recycle View
        val recyclerView =  vista.findViewById<RecyclerView>(R.id.rv_libros)
        inicializarRecyclerView(listaLibro, recyclerView)
        return vista
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
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
    }

}