package com.example.app_cursos.Vistas

import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.PopupMenu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_cursos.Adaptador.cursosAdaptador
import com.example.app_cursos.modelo.dataCursos
import android.widget.ArrayAdapter
import com.example.app_cursos.R
import com.example.app_cursos.presentador.clsCatalogoPresentador

class MainActivity : AppCompatActivity() {

    private lateinit var spnCarrera: Spinner
    private lateinit var rvCursos: RecyclerView
    private lateinit var btnVerRegistros: Button
    private lateinit var txtMenu: TextView

    //linea hecha por tello-lo ocupaba por eso
    private lateinit var adapterCursos: cursosAdaptador
    private lateinit var presentadorCatalogo: clsCatalogoPresentador
    private var listaTodosLosCursos: List<dataCursos> = emptyList()
    private var idUsuario: Int = -1
    private var matriculaUsuario: String = ""
    private var nombreUsuario: String = ""
    private var correoUsuario: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        spnCarrera = findViewById(R.id.spnCarrera)
        rvCursos = findViewById(R.id.rvCursos)
        btnVerRegistros = findViewById(R.id.btnVerRegistros)
        txtMenu = findViewById(R.id.txtMenu)
        idUsuario = intent.getIntExtra("id_usuario", -1)
        matriculaUsuario = intent.getStringExtra("matricula") ?: ""
        nombreUsuario = intent.getStringExtra("nombre_usuario") ?: ""
        correoUsuario = intent.getStringExtra("correo_usuario") ?: ""

        //codigo hecho por tellos
        adapterCursos = cursosAdaptador(this, emptyList())
        presentadorCatalogo = clsCatalogoPresentador()

        configurarRecyclerView()
        cargarCursos()
        cargarCarreras()

        btnVerRegistros.setOnClickListener {
            irARegistros()
        }

        txtMenu.setOnClickListener {
            mostrarMenu()
        }

    }

    private fun mostrarMenu() {
        val menu = PopupMenu(this, txtMenu)

        menu.menu.add("Cerrar sesión")

        menu.setOnMenuItemClickListener { item ->
            when (item.title) {
                "Cerrar sesión" -> {
                    cerrarSesion()
                    true
                }

                else -> false
            }
        }

        menu.show()
    }

    private fun cerrarSesion() {
        val intent = Intent(this, LoginActivity::class.java)

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                Intent.FLAG_ACTIVITY_CLEAR_TASK

        startActivity(intent)
        finish()
    }


    private fun configurarRecyclerView() {
        rvCursos.layoutManager = GridLayoutManager(this, 2)
        rvCursos.adapter = adapterCursos
    }
    private fun cargarCursos() {
        presentadorCatalogo.obtenerCursos(this) { listaCursos ->
            listaTodosLosCursos = listaCursos
            adapterCursos.actualizarLista(listaCursos)
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
            val adapterCarreras = ArrayAdapter(this, android.R.layout.simple_spinner_item, nombresCarreras)
            adapterCarreras.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnCarrera.adapter = adapterCarreras
            spnCarrera.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position == 0) {
                        // "Todos los cursos"
                        adapterCursos.actualizarLista(listaTodosLosCursos)
                    } else {
                        // Restamos 1 porque la posición 0 es "Todos los cursos"
                        val carreraSeleccionada = listaCarreras[position - 1]

                        val cursosFiltrados = listaTodosLosCursos.filter { curso ->
                            curso.idCarrera == carreraSeleccionada.id_carrera
                        }

                        adapterCursos.actualizarLista(cursosFiltrados)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No hacemos nada
                }
            }
        }
    }

    private fun irARegistros() {
        val intent = Intent(this, VerRegistrosActivity::class.java)
        startActivity(intent)
    }

    fun seleccionarCurso(curso: dataCursos) {
        val intent = Intent(this, DetalleCursoActivity::class.java).apply {
            putExtra("id_curso", curso.id)

            putExtra("id_usuario", idUsuario)
            putExtra("matricula", matriculaUsuario)
            putExtra("nombre_usuario", nombreUsuario)
            putExtra("correo_usuario", correoUsuario)
        }

        startActivity(intent)
    }
}