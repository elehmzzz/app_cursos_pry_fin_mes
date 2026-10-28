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
import com.example.app_cursos.presentador.clsInscripcionPresentador
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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

    private var idCurso: Int = -1
    private var idUsuario: Int = -1

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

        // 1. Recibir datos enviados desde DetalleCursoActivity o Login
        idCurso = intent.getIntExtra("id_curso", -1)
        idUsuario = intent.getIntExtra("id_usuario", -1)
        val nombreCurso = intent.getStringExtra("nombre_curso") ?: ""
        val codigoCurso = intent.getStringExtra("codigo_curso") ?: ""
        val matriculaUsuario = intent.getStringExtra("matricula") ?: ""
        val nombreUsuario = intent.getStringExtra("nombre_usuario") ?: ""
        val correoUsuario = intent.getStringExtra("correo_usuario") ?: ""

        // 2. Mostrar la información real en la vista
        txtNombreCurso.text = nombreCurso
        txtCodigoCurso.text = "Código: $codigoCurso"
        txtMatricula.text = "Matrícula: $matriculaUsuario"
        txtNombreAlumno.text = "Alumno: $nombreUsuario"
        txtCorreo.text = "Correo: $correoUsuario"

        // Generar la fecha actual del sistema para mostrarla en pantalla
        val fechaActual = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        txtFechaInscripcion.text = "Fecha de inscripción: $fechaActual"
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
        if (idCurso == -1 || idUsuario == -1) {
            return
        }

        val presentador = clsInscripcionPresentador()
        presentador.registrarInscripcion(this, idUsuario, idCurso) {
            finish()
        }
    }

    private fun cancelarInscripcion() {
        finish()
    }
}