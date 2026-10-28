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
import com.example.app_cursos.R
import com.example.app_cursos.presentador.clsCatalogoPresentador

class MainActivity : AppCompatActivity() {

    private lateinit var spnCarrera: Spinner
    private lateinit var rvCursos: RecyclerView
    private lateinit var btnVerRegistros: Button
    private lateinit var txtMenu: TextView

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

        configurarRecyclerView()

        btnVerRegistros.setOnClickListener {
            irARegistros()
        }

        cargarCursos()
        // TODO: Cargar las carreras en spnCarrera
        // cuando esté disponible el Presenter/API correspondiente.

        // TODO: Cargar los cursos en rvCursos
        // cuando esté disponible el Presenter/API correspondiente.

        // TODO: Agregar las opciones de txtMenu
        // cuando se definan sus funciones.
    }

    private fun configurarRecyclerView() {
        rvCursos.layoutManager = GridLayoutManager(this, 2)
    }
    private fun cargarCursos() {
        val presentador = clsCatalogoPresentador()
        presentador.obtenerCursos(this) { listaCursos ->
            // TODO: aqui le quitan los comentarios depentiendo su logica del adaptador, asignar 'listaCursos' al adaptador del RecyclerView
            //val adaptador = CursoAdapter(listaCursos)
            //rvCursos.adapter = adaptador
        }
    }

    private fun irARegistros() {
        val intent = Intent(this, VerRegistrosActivity::class.java)
        startActivity(intent)
    }
}