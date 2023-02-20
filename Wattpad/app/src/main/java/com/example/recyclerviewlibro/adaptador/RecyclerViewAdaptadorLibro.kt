package com.example.recyclerviewlibro.adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewlibro.fragments.LibrosRecyclerView
import com.example.recyclerviewlibro.R
import com.example.recyclerviewlibro.clases.Libro

class RecyclerViewAdaptadorLibro (
    private val contexto: LibrosRecyclerView,
    private val lista: ArrayList<Libro>,
    private val recyclerView: RecyclerView
):RecyclerView.Adapter<RecyclerViewAdaptadorLibro.MyViewHolder>(){
    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        val tituloTextView: TextView
        val autorTextView: TextView
        //val resumenTextView: TextView
        //val accionButton: Button

        init{
            tituloTextView = view.findViewById(R.id.idTitulo)
            autorTextView = view.findViewById(R.id.idAutor)
            //accionButton = view.findViewById<Button>(R.id.btn_menu_capitulos)
            //accionButton.setOnClickListener{}
        }
    }

    //setear el layout que vamos a utilizar.
    override fun onCreateViewHolder(
        parent:ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.libro_list,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    //setear los datos para la iteración
    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        val libroActual = this.lista[position]
        holder.tituloTextView.text = libroActual.nombre
        holder.autorTextView.text = libroActual.resumen
        //holder.accionButton.text = ""
    }

    //tamaño del arreglo
    override fun getItemCount(): Int{
        return this.lista.size
    }
}