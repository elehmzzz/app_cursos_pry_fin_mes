package com.example.app_cursos.Vistas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_cursos.R

class RegistroActivity : AppCompatActivity() {

    private lateinit var edtMatricula: EditText
    private lateinit var edtNombre: EditText
    private lateinit var edtApellidoPaterno: EditText
    private lateinit var edtApellidoMaterno: EditText
    private lateinit var edtCorreo: EditText
    private lateinit var edtContrasena: EditText
    private lateinit var btnRegistrar: Button
    private lateinit var txtIniciarSesion: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edtMatricula = findViewById(R.id.edtMatricula)
        edtNombre = findViewById(R.id.edtNombre)
        edtApellidoPaterno = findViewById(R.id.edtApellidoPaterno)
        edtApellidoMaterno = findViewById(R.id.edtApellidoMaterno)
        edtCorreo = findViewById(R.id.edtCorreo)
        edtContrasena = findViewById(R.id.edtContrasena)
        btnRegistrar = findViewById(R.id.btnRegistrar)
        txtIniciarSesion = findViewById(R.id.txtIniciarSesion)

        btnRegistrar.setOnClickListener {
            registrarUsuario()
        }

        txtIniciarSesion.setOnClickListener {
            irALogin()
        }
    }

    private fun registrarUsuario() {
        val matricula = edtMatricula.text.toString().trim()
        val nombre = edtNombre.text.toString().trim()
        val apellidoPaterno = edtApellidoPaterno.text.toString().trim()
        val apellidoMaterno = edtApellidoMaterno.text.toString().trim()
        val correo = edtCorreo.text.toString().trim()
        val contrasena = edtContrasena.text.toString().trim()

        if (matricula.isEmpty()) {
            edtMatricula.error = "Ingresa tu matrícula"
            edtMatricula.requestFocus()
            return
        }

        if (nombre.isEmpty()) {
            edtNombre.error = "Ingresa tu nombre"
            edtNombre.requestFocus()
            return
        }

        if (apellidoPaterno.isEmpty()) {
            edtApellidoPaterno.error = "Ingresa tu apellido paterno"
            edtApellidoPaterno.requestFocus()
            return
        }

        if (apellidoMaterno.isEmpty()) {
            edtApellidoMaterno.error = "Ingresa tu apellido materno"
            edtApellidoMaterno.requestFocus()
            return
        }

        if (correo.isEmpty()) {
            edtCorreo.error = "Ingresa tu correo institucional"
            edtCorreo.requestFocus()
            return
        }

        if (contrasena.isEmpty()) {
            edtContrasena.error = "Ingresa tu contraseña"
            edtContrasena.requestFocus()
            return
        }

        // TODO: Enviar los datos al Presenter
        // cuando el Presenter de registro esté disponible.
    }

    private fun irALogin() {
        finish()
    }
}