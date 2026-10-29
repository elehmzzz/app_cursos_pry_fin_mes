package com.example.app_cursos.presentador

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.app_cursos.Vistas.MainActivity
import com.example.app_cursos.modelo.RetrofitCliente
import com.example.app_cursos.modelo.clsLoginRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.security.auth.callback.Callback

class clsLoginPresentador {
    fun autenticar(context: Context, matricula: String, contrasena: String) {
        val request = clsLoginRequest(matricula, contrasena)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val resultado = RetrofitCliente.apiService.iniciarSesion(request)
                withContext(Dispatchers.Main) {
                    if (resultado.exito) {
                        Toast.makeText(context, resultado.mensaje, Toast.LENGTH_SHORT).show()
                        val intent = Intent(context, MainActivity::class.java).apply {
                            putExtra("id_usuario", resultado.id_usuario)
                            putExtra("matricula", resultado.matricula)
                            putExtra("nombre_usuario", "${resultado.nombre} ${resultado.apellido_paterno}")
                            putExtra("correo_usuario", resultado.correo_institucional)
                        }
                        context.startActivity(intent)
                    } else {
                        Toast.makeText(context, resultado.mensaje, Toast.LENGTH_LONG).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Error de conexión: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}