
package com.example.app_cursos.modelo

import kotlinx.serialization.Serializable

@Serializable
data class ClsProductoModelos(
    val id_curso: Int = 0,
    val codigo: String = "",
    val nombre: String = "",
    val descripcion: String = "",
    val espacio: String = "",
    val salon: String = "",
    val total_horas: Int = 0,
    val dias_atencion: String = "",
    val horario: String = "",
    val cupo_maximo: Int = 0,
    val imagen: String = "",
    val id_carrera: Int = 0
)
