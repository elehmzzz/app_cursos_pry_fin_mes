package com.example.app_cursos.presentador

import android.content.Context
import android.widget.Toast
import com.example.app_cursos.modelo.RetrofitCliente
import com.example.app_cursos.modelo.clsCurso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class clsCatalogoPresentador {
    fun obtenerCursos(context: Context, onCursosObtenidos: (List<clsCurso>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val listaCursos = RetrofitCliente.apiService.obtenerCursos()
                withContext(Dispatchers.Main) {
                    onCursosObtenidos(listaCursos)
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