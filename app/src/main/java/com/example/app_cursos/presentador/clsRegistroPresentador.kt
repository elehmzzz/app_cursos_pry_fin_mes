package com.example.app_cursos.presentador

import android.content.Context
import android.widget.Toast
import com.example.app_cursos.modelo.RetrofitCliente
import com.example.app_cursos.modelo.clsRegistroRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class clsRegistroPresentador {
    fun registrar(context: Context, matricula: String, nombre: String, apellidoPaterno: String, apellidoMaterno: String, correo: String, contrasena: String, onRegistroExitoso: () -> Unit
    ){
        val request = clsRegistroRequest(
            matricula = matricula,
            nombre = nombre,
            apellido_paterno = apellidoPaterno,
            apellido_materno = apellidoMaterno,
            correo_institucional = correo,
            contrasena = contrasena
        )
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val resultado = RetrofitCliente.apiService.registrarUsuario(request)
                withContext(Dispatchers.Main) {
                    if (resultado.exito) {
                        Toast.makeText(context, resultado.mensaje, Toast.LENGTH_SHORT).show()
                        onRegistroExitoso()
                    } else {
                        Toast.makeText(context, resultado.mensaje, Toast.LENGTH_LONG).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Error de conexión: ${e.message}", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
}