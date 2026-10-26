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
import com.example.app_cursos.R

class VerRegistrosActivity : AppCompatActivity() {

    private lateinit var txtRegresar: TextView
    private lateinit var spnCarrera: Spinner
    private lateinit var txtTotalInscritos: TextView
    private lateinit var rvRegistros: RecyclerView

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

        configurarRecyclerView()

        txtRegresar.setOnClickListener {
            finish()
        }

        // TODO: Cargar las carreras en spnCarrera
        // cuando esté disponible el Presenter/API correspondiente.

        // TODO: Cargar los registros de inscripción
        // y mostrar el total de inscritos cuando esté
        // disponible el Presenter/API correspondiente.
    }

    private fun configurarRecyclerView() {
        rvRegistros.layoutManager = LinearLayoutManager(this)
    }
}