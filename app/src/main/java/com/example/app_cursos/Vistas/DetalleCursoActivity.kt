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
import com.example.app_cursos.presentador.clsDetalleCursoPresentador

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
    private var idCursoActual: Int = -1
    private var codigoDelCurso: String = ""

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
        /* Obtener el ID real enviado desde el catálogo
        idCursoActual = intent.getIntExtra("id_curso", -1)

        if (idCursoActual != -1) {
            cargarDetalleCurso(idCursoActual)
        }*/

        /* evento de clic en el botón "Ver Detalle" dentro del adaptador del RecyclerView asi que copean esto y borran de aqui
        val intent = Intent(context, DetalleCursoActivity::class.java).apply {
            putExtra("id_curso", curso.id_curso)
        }
        context.startActivity(intent)*/

        btnInscribirse.setOnClickListener {
            irAInscripcion()
        }
    }
    private fun cargarDetalleCurso(idCurso: Int) {
        val presentador = clsDetalleCursoPresentador()
        presentador.obtenerDetalle(this, idCurso) { detalle ->
            codigoDelCurso = detalle.codigo
            txtNombreCurso.text = detalle.nombre
            txtCodigoCurso.text = "Código: ${detalle.codigo}"
            txtDescripcion.text = detalle.descripcion
            txtSalon.text = "Salón: ${detalle.salon}"
            txtEdificio.text = "Edificio: ${detalle.espacio}"
            txtTotalHoras.text = "Total de horas: ${detalle.total_horas}"
            txtDiasAtencion.text = "Días de atención: ${detalle.dias_atencion}"
            txtHorario.text = "Horario: ${detalle.horario}"
        }
    }

    private fun irAInscripcion() {
        val intent = Intent(this, InscripcionActivity::class.java).apply {
            putExtra("id_curso", idCursoActual)
            putExtra("nombre_curso", txtNombreCurso.text.toString())
            putExtra("codigo_curso", codigoDelCurso)
        }
        startActivity(intent)
    }
}