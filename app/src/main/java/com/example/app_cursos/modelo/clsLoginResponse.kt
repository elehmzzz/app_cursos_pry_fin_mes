package com.example.app_cursos.modelo
import kotlinx.serialization.Serializable

@Serializable
data class clsLoginResponse (val exito: Boolean, val mensaje: String, val id_usuario: Int = 0, val matricula: String = "", val nombre: String = "", val apellido_paterno: String = "", val correo_institucional: String = "") {
}