package com.example.app_cursos.Vistas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_cursos.R

class DetalleCursoActivity : AppCompatActivity() {

    private lateinit var txtRegresar: TextView
    private lateinit var imgCursoDetalle: ImageView
    private lateinit var txtNombreCurso: TextView
    private lateinit var txtCodigoCurso: TextView
    private lateinit var txtDescripcion: TextView
    private lateinit var txtSalon: TextView
    private lateinit var txtEdificio: TextView
    private lateinit var txtTotalHoras: TextView
    private lateinit var txtDiasAtencion: TextView
    private lateinit var txtHorario: TextView
    private lateinit var btnInscribirse: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_curso)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtRegresar = findViewById(R.id.txtRegresar)
        imgCursoDetalle = findViewById(R.id.imgCursoDetalle)
        txtNombreCurso = findViewById(R.id.txtNombreCurso)
        txtCodigoCurso = findViewById(R.id.txtCodigoCurso)
        txtDescripcion = findViewById(R.id.txtDescripcion)
        txtSalon = findViewById(R.id.txtSalon)
        txtEdificio = findViewById(R.id.txtEdificio)
        txtTotalHoras = findViewById(R.id.txtTotalHoras)
        txtDiasAtencion = findViewById(R.id.txtDiasAtencion)
        txtHorario = findViewById(R.id.txtHorario)
        btnInscribirse = findViewById(R.id.btnInscribirse)

        txtRegresar.setOnClickListener {
            finish()
        }

        btnInscribirse.setOnClickListener {
            irAInscripcion()
        }

        // TODO: Recibir/obtener el curso seleccionado
        // y mostrar sus datos cuando esté disponible
        // el Presenter/API correspondiente.
    }

    private fun irAInscripcion() {
        val intent = Intent(this, InscripcionActivity::class.java)
        startActivity(intent)
    }
}