package com.example.app_cursos.Vistas

import android.os.Bundle
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_cursos.Adaptador.estadoInscripcionesAdaptador
import com.example.app_cursos.R
import com.example.app_cursos.presentador.clsVerRegistrosPresentador

class VerRegistrosActivity : AppCompatActivity() {

    private lateinit var txtRegresar: TextView
    private lateinit var spnCarrera: Spinner
    private lateinit var txtTotalInscritos: TextView
    private lateinit var rvRegistros: RecyclerView

    //se incluye el adaptador del estado de inscripcion--agregado:tello
    private lateinit var adapterEstadoProducto: estadoInscripcionesAdaptador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ver_registros)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtRegresar = findViewById(R.id.txtRegresar)
        spnCarrera = findViewById(R.id.spnCarrera)
        txtTotalInscritos = findViewById(R.id.txtTotalInscritos)
        rvRegistros = findViewById(R.id.rvRegistros)

        //al adaptadorr se le pasa solo un emptyList() sin el activity verRegistroActivity--agregado:tello
        adapterEstadoProducto = estadoInscripcionesAdaptador(emptyList())

        configurarRecyclerView()

        txtRegresar.setOnClickListener {
            finish()
        }
        cargarRegistros()

        // TODO: Cargar las carreras en spnCarrera
        // cuando esté disponible el Presenter/API correspondiente.

        // TODO: Cargar los registros de inscripción
        // y mostrar el total de inscritos cuando esté
        // disponible el Presenter/API correspondiente.
    }

    private fun configurarRecyclerView() {
        rvRegistros.layoutManager = LinearLayoutManager(this)
    }
    private fun cargarRegistros() {
        val presentador = clsVerRegistrosPresentador()
        presentador.obtenerRegistros(this) { listaRegistros ->
            // 1. Mostrar el total de inscritos en el TextView
            txtTotalInscritos.text = "Total de inscritos: ${listaRegistros.size}"

            // 2. Aquí asignan la lista al adaptador del RecyclerView:
            // val adaptador = RegistrosAdapter(listaRegistros)
            // rvRegistros.adapter = adaptador
        }
    }
}