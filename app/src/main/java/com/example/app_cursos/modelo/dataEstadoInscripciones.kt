package com.example.app_cursos.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataEstadoInscripciones(
    @SerialName("id_curso") val id: Int = 0,
    @SerialName("codigo") val codigo: String = "",
    @SerialName("nombre") val nombre: String = "",
    @SerialName("cupo_maximo") val cupoMaximo: Int = 0,
    @SerialName("imagen") val urlImagen: String = "",
    @SerialName("id_carrera") val idCarrera: Int = 0,
    @SerialName("total_inscritos") val totalInscritos: Int = 0
)