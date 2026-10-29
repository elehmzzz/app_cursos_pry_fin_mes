package com.example.app_cursos.modelo

import kotlinx.serialization.Serializable

@Serializable
data class clsEstadoCursosResponse(
    val success: Boolean,
    val cursos: List<dataEstadoInscripciones> = emptyList(),
    val message: String = ""
)