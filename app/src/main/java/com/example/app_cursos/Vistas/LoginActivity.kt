package com.example.app_cursos.Vistas

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_cursos.R
import com.example.app_cursos.presentador.clsLoginPresentador


class LoginActivity : AppCompatActivity() {

    private lateinit var edtMatricula: EditText
    private lateinit var edtContrasena: EditText
    private lateinit var btnLogin: Button
    private lateinit var txtRegistro: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding( systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edtMatricula = findViewById(R.id.edtMatricula)
        edtContrasena = findViewById(R.id.edtContrasena)
        btnLogin = findViewById(R.id.btnLogin)
        txtRegistro = findViewById(R.id.txtRegistro)

        btnLogin.setOnClickListener {
            iniciarSesion()
        }

        txtRegistro.setOnClickListener {
            irARegistro()
        }
    }

    private fun iniciarSesion() {
        val matricula = edtMatricula.text.toString().trim()
        val contrasena = edtContrasena.text.toString().trim()

        if (matricula.isEmpty()) {
            edtMatricula.error = "Ingresa tu matrícula"
            edtMatricula.requestFocus()
            return
        }

        if (contrasena.isEmpty()) {
            edtContrasena.error = "Ingresa tu contraseña"
            edtContrasena.requestFocus()
            return
        }

        // TODO: Enviar matrícula y contraseña al Presenter
    }

    private fun irARegistro() {
        val intent = Intent(this, RegistroActivity::class.java)
        startActivity(intent)
    }
}