package com.example.app_cursos.modelo
import kotlinx.serialization.Serializable

@Serializable
data class clsRegistroResponse (val exito: Boolean, val mensaje: String) {
}