package com.example.app_cursos.modelo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataEstadoInscripciones(
    @SerialName("id_curso") val id: Int = 0,
    @SerialName("nombre") val nombre: String = "",
    @SerialName("cupo_maximo") val cupo_maximo: String = "0.0",
    @SerialName("imagen") val urlImagen:String = ""
) {
}