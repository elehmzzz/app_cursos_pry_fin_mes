package com.example.app_cursos.Vistas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_cursos.Adaptador.cursosAdaptador
import com.example.app_cursos.modelo.dataCursos
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

        //codigo hecho por tellos
        adapterCursos = cursosAdaptador(this, emptyList())
        presentadorCatalogo = clsCatalogoPresentador()

        configurarRecyclerView()
        cargarCursos()

        btnVerRegistros.setOnClickListener {
            irARegistros()
        }
        // TODO: Cargar las carreras en spnCarrera
        // cuando esté disponible el Presenter/API correspondiente.

        // TODO: Agregar las opciones de txtMenu
        // cuando se definan sus funciones.
    }

    private fun configurarRecyclerView() {
        rvCursos.layoutManager = GridLayoutManager(this, 2)
        rvCursos.adapter = adapterCursos
    }
    private fun cargarCursos() {
        presentadorCatalogo.obtenerCursos(this) { listaCursos ->
            adapterCursos.actualizarLista(listaCursos)
        }
    }

    private fun irARegistros() {
        val intent = Intent(this, VerRegistrosActivity::class.java)
        startActivity(intent)
    }

    fun seleccionarCurso(curso: dataCursos) {
        val intent = Intent(this, DetalleCursoActivity::class.java)
        intent.putExtra("id_curso", curso.id)
        startActivity(intent)
    }
}