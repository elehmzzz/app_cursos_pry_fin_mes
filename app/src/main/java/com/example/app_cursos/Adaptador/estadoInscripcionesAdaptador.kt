package com.example.app_cursos.Adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app_cursos.Vistas.InscripcionActivity
import com.example.app_cursos.R
import com.example.app_cursos.Vistas.VerRegistrosActivity
import com.example.app_cursos.modelo.dataEstadoInscripciones

class estadoInscripcionesAdaptador( private var listaInscripciones: List<dataEstadoInscripciones>
): RecyclerView.Adapter<estadoInscripcionesAdaptador.estadoInscricionesAdaptadorViewHolder>() {

    class estadoInscricionesAdaptadorViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvNombreCurso: TextView = itemView.findViewById(R.id.txtNombreCurso)
        val tvCantInscritos: TextView = itemView.findViewById(R.id.txtCantidadInscritos)
        val tvCupoTotal: TextView = itemView.findViewById(R.id.txtCupoTotal)
        val imgCurso: ImageView = itemView.findViewById(R.id.imgCurso)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): estadoInscricionesAdaptadorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_registro, parent, false)
        return estadoInscricionesAdaptadorViewHolder(view)
    }

    override fun onBindViewHolder(holder: estadoInscricionesAdaptadorViewHolder, position: Int) {
        val inscripcion = listaInscripciones[position]
        holder.tvNombreCurso.text = inscripcion.nombre
        holder.tvCupoTotal.text = inscripcion.cupo_maximo
        //falta un holder para el total de alumnos inscritos
        val baseUrl = "http://10.10.10.10/app_cursos_api/"
        val urlImagenCompleta = baseUrl + inscripcion.urlImagen
        Glide.with(holder.itemView.context)
            .load(urlImagenCompleta)
            .placeholder(android.R.drawable.ic_menu_gallery)//imagen por defecto mientras carga
            .error(android.R.drawable.stat_notify_error)//imagen si falla la carga
            .into(holder.imgCurso)
    }

    override fun getItemCount(): Int {
        return listaInscripciones.size
    }

    fun actualizarLista(nuevaLista: List<dataEstadoInscripciones>) {
        listaInscripciones = nuevaLista
        notifyDataSetChanged()
    }


}