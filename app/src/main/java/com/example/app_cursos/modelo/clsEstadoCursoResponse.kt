package com.example.app_cursos.modelo
import kotlinx.serialization.Serializable

@Serializable
data class clsEstadoCursoResponse(val id_registro: Int, val nombre_curso: String, val codigo: String, val fecha_inscripcion: String, val estado: String) {
}