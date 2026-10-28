package com.example.app_cursos.presentador

import android.content.Context
import android.widget.Toast
import com.example.app_cursos.modelo.RetrofitCliente
import com.example.app_cursos.modelo.clsInscripcionRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class clsInscripcionPresentador {
    fun registrarInscripcion(
        context: Context,
        idUsuario: Int,
        idCurso: Int,
        onInscripcionExitosa: () -> Unit
    ) {
        val request = clsInscripcionRequest(
            id_usuario = idUsuario,
            id_curso = idCurso
        )

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val resultado = RetrofitCliente.apiService.inscribirseCurso(request)
                withContext(Dispatchers.Main) {
                    if (resultado.exito) {
                        Toast.makeText(context, resultado.mensaje, Toast.LENGTH_SHORT).show()
                        onInscripcionExitosa()
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