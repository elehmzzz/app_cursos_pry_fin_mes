package com.example.app_cursos.modelo

class clsCursoModelo {
    suspend fun obtenerCursos(): List<dataCursos> {
        return RetrofitCliente.apiService.obtenerCursos()
    }
    suspend fun obtenerDetalleCurso(idCurso: Int): clsDetalleCursoResponse {
        return RetrofitCliente.apiService.obtenerDetalleCurso(idCurso)
    }
}