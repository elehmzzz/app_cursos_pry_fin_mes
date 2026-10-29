package com.example.app_cursos.presentador

import android.content.Context
import android.widget.Toast
import com.example.app_cursos.modelo.RetrofitCliente
import com.example.app_cursos.modelo.clsEstadoCursoResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class clsVerRegistrosPresentador {
    fun obtenerRegistros(context: Context, onRegistrosObtenidos: (List<clsEstadoCursoResponse>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val listaRegistros = RetrofitCliente.apiService.obtenerEstadocursos()
                withContext(Dispatchers.Main) {
                    onRegistrosObtenidos(listaRegistros)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Error de conexión: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}