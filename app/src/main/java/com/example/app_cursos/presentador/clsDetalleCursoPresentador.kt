package com.example.app_cursos.presentador

import android.content.Context
import android.widget.Toast
import com.example.app_cursos.modelo.clsCursoModelo
import com.example.app_cursos.modelo.clsDetalleCursoResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class clsDetalleCursoPresentador {
    private val modelo = clsCursoModelo()
    fun obtenerDetalle(context: Context, idCurso: Int, onDetalleObtenido: (clsDetalleCursoResponse) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val detalle = modelo.obtenerDetalleCurso(idCurso)
                withContext(Dispatchers.Main) {
                    onDetalleObtenido(detalle)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Error de conexión: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}