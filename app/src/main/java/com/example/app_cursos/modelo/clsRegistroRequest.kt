package com.example.app_cursos.modelo
import kotlinx.serialization.Serializable

@Serializable
data class clsRegistroRequest (val matricula: String, val nombre: String, val apellido_paterno: String, val apellido_materno: String, val correo_institucional: String, val contrasena: String) {
}