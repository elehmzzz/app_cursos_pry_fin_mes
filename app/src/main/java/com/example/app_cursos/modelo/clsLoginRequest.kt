package com.example.app_cursos.modelo
import kotlinx.serialization.Serializable

@Serializable
data class clsLoginRequest(val matricula: String, val contrasena: String) {
}