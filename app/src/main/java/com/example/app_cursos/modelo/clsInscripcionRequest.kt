package com.example.app_cursos.modelo
import kotlinx.serialization.Serializable

@Serializable
data class clsInscripcionRequest(val id_usuario: Int, val id_curso: Int) {
}