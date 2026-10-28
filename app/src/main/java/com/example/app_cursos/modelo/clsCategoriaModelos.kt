package com.example.app_cursos.modelo


import kotlinx.serialization.Serializable

@Serializable
data class clsCategoriaModelos(
    val id_carrera: Int,
    val nombre: String
)
