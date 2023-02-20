package com.example.recyclerviewlibro.adaptador
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewlibro.LecturasActivity
import com.example.recyclerviewlibro.R
import com.example.recyclerviewlibro.clases.Lecturas
import com.example.recyclerviewlibro.fragments.ReadingLists

class LecturasAdapter (
    private val contexto: ReadingLists,
    private val lista: ArrayList<Lecturas>,
    private val recyclerView: RecyclerView
    //private val itemClickListener: OnListClickListener
):RecyclerView.Adapter<LecturasAdapter.MyViewHolder>(){

    /*interface OnListClickListener{
        fun onItemClick(libros: ArrayList<Libro>?)
    }*/
    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view){

        val tituloTextView: TextView
        val autorTextView: TextView
        val imagenView : ImageView

        init{
            tituloTextView = view.findViewById(R.id.idTitulo)
            autorTextView = view.findViewById(R.id.idDescripcion)
            imagenView = view.findViewById(R.id.idImagen)
            itemView.setOnClickListener {
                //val view = View.inflate()
                val intent = Intent(view.context, LecturasActivity::class.java)
                view.context.startActivity(intent)
            }
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
                R.layout.lectura_list,
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

        val lecturaActual = this.lista[position]
        holder.tituloTextView.text = lecturaActual.nombre
        holder.autorTextView.text = lecturaActual.autor
        holder.imagenView.setImageResource(lecturaActual.imagen)
    }

    //tamaño del arreglo
    override fun getItemCount(): Int{
        return this.lista.size
    }

}