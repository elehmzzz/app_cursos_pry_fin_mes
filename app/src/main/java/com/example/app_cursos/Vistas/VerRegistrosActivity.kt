package com.example.app_cursos.Vistas

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
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
import com.example.app_cursos.modelo.dataEstadoInscripciones
import com.example.app_cursos.presentador.clsCatalogoPresentador
import com.example.app_cursos.presentador.clsVerRegistrosPresentador

class VerRegistrosActivity : AppCompatActivity() {

    private lateinit var txtRegresar: TextView
    private lateinit var spnCarrera: Spinner
    private lateinit var txtTotalInscritos: TextView
    private lateinit var rvRegistros: RecyclerView

    private lateinit var adapterEstadoProducto: estadoInscripcionesAdaptador
    private lateinit var presentadorRegistros: clsVerRegistrosPresentador
    private lateinit var presentadorCatalogo: clsCatalogoPresentador

    private var listaTodosLosRegistros: List<dataEstadoInscripciones> = emptyList()

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

        adapterEstadoProducto = estadoInscripcionesAdaptador(emptyList())
        presentadorRegistros = clsVerRegistrosPresentador()
        presentadorCatalogo = clsCatalogoPresentador()

        configurarRecyclerView()
        cargarRegistros()
        cargarCarreras()

        txtRegresar.setOnClickListener {
            finish()
        }
    }

    private fun configurarRecyclerView() {
        rvRegistros.layoutManager = LinearLayoutManager(this)
        rvRegistros.adapter = adapterEstadoProducto
    }

    private fun cargarRegistros() {
        presentadorRegistros.obtenerRegistros(this) { listaRegistros ->

            listaTodosLosRegistros = listaRegistros

            adapterEstadoProducto.actualizarLista(listaRegistros)

            actualizarTotal(listaRegistros)
        }
    }

    private fun cargarCarreras() {
        presentadorCatalogo.obtenerCarreras(this) { listaCarreras ->

            val nombresCarreras = mutableListOf("Todos los cursos")

            nombresCarreras.addAll(
                listaCarreras.map { carrera ->
                    carrera.nombre
                }
            )

            val adapterCarreras = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                nombresCarreras
            )

            adapterCarreras.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
            )

            spnCarrera.adapter = adapterCarreras

            spnCarrera.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        if (position == 0) {

                            adapterEstadoProducto.actualizarLista(
                                listaTodosLosRegistros
                            )

                            actualizarTotal(listaTodosLosRegistros)

                        } else {

                            val carreraSeleccionada =
                                listaCarreras[position - 1]

                            val registrosFiltrados =
                                listaTodosLosRegistros.filter { registro ->
                                    registro.idCarrera ==
                                            carreraSeleccionada.id_carrera
                                }

                            adapterEstadoProducto.actualizarLista(
                                registrosFiltrados
                            )

                            actualizarTotal(registrosFiltrados)
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        // No hacemos nada
                    }
                }
        }
    }

    private fun actualizarTotal(
        listaRegistros: List<dataEstadoInscripciones>
    ) {
        val totalInscritos = listaRegistros.sumOf { registro ->
            registro.totalInscritos
        }

        txtTotalInscritos.text =
            "Total de inscritos: $totalInscritos"
    }
}