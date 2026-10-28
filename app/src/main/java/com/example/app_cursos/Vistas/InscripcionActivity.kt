package com.example.app_cursos.Vistas

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_cursos.R

class InscripcionActivity : AppCompatActivity() {

    private lateinit var imgCurso: ImageView
    private lateinit var txtNombreCurso: TextView
    private lateinit var txtCodigoCurso: TextView
    private lateinit var txtMatricula: TextView
    private lateinit var txtNombreAlumno: TextView
    private lateinit var txtCorreo: TextView
    private lateinit var txtFechaInscripcion: TextView
    private lateinit var btnConfirmarInscripcion: Button
    private lateinit var btnCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inscripcion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imgCurso = findViewById(R.id.imgCurso)
        txtNombreCurso = findViewById(R.id.txtNombreCurso)
        txtCodigoCurso = findViewById(R.id.txtCodigoCurso)
        txtMatricula = findViewById(R.id.txtMatricula)
        txtNombreAlumno = findViewById(R.id.txtNombreAlumno)
        txtCorreo = findViewById(R.id.txtCorreo)
        txtFechaInscripcion = findViewById(R.id.txtFechaInscripcion)
        btnConfirmarInscripcion = findViewById(R.id.btnConfirmarInscripcion)
        btnCancelar = findViewById(R.id.btnCancelar)

        btnConfirmarInscripcion.setOnClickListener {
            confirmarInscripcion()
        }

        btnCancelar.setOnClickListener {
            cancelarInscripcion()
        }

        // TODO: Mostrar los datos del curso y del alumno
        // cuando esté disponible el Presenter/API correspondiente.
    }

    private fun confirmarInscripcion() {
        // TODO: Enviar la confirmación al Presenter
        // cuando esté disponible.
    }

    private fun cancelarInscripcion() {
        finish()
    }
}