package com.example.app_cursos.Adaptador

import android.view.LayoutInflater
import android.media.Image
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.app_cursos.R
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app_cursos.Vistas.DetalleCursoActivity
import com.example.app_cursos.Vistas.MainActivity
import com.example.app_cursos.modelo.dataCursos

class cursosAdaptador(private val activity: MainActivity, private var listaCursos: List<dataCursos>
): RecyclerView.Adapter<cursosAdaptador.cursosAdaptadorViewHolder>() {

    class cursosAdaptadorViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvNombre: TextView = itemView.findViewById(R.id.txtNombreCurso)
        val tvSalon: TextView = itemView.findViewById(R.id.txtSalon)
        val imgCurso: ImageView = itemView.findViewById(R.id.imgCurso)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cursosAdaptadorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_curso, parent, false)
        return cursosAdaptadorViewHolder(view)
    }


    override fun onBindViewHolder( holder: cursosAdaptadorViewHolder, position: Int) {
        val curso = listaCursos[position]
        holder.tvNombre.text = curso.nombre
        holder.tvSalon.text = curso.salon
        val baseUrl = "http://10.10.10.10/app_cursos_api/"
        val urlImagenCompleta = baseUrl + curso.urlImagen
        Glide.with(holder.itemView.context)
            .load(urlImagenCompleta)
            .placeholder(android.R.drawable.ic_menu_gallery)//imagen por defecto mientras carga
            .error(android.R.drawable.stat_notify_error)//imagen si falla la carga
            .into(holder.imgCurso)

        holder.itemView.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                //esta funcion se ejecuta en la vista MainActivity
                //y se manda al presentador para que muestre el curso seleccionado
                activity.seleccionarCurso(curso)
            }
        })

    }

    override fun getItemCount(): Int {
        return listaCursos.size
    }

    fun actualizarLista(nuevaLista: List<dataCursos>){
        listaCursos = nuevaLista
        notifyDataSetChanged()
    }

}