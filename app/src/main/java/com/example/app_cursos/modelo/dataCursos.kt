package com.example.app_cursos.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataCursos (
    @SerialName("id_curso") val id: Int = 0,
    @SerialName("codigo") val codigo:Int = 0,
    @SerialName("nombre") val nombre: String = "",
    @SerialName("descripcion") val  descripcion: String = "sin descripcion",
    @SerialName("espacio") val espacio: String = "sin espacio",
    @SerialName("salon") val salon: String = "sin salon",
    @SerialName("total_horas") val total_horas: String = "0.0",
    @SerialName("dias_atencion") val dias_atencion: String = "",
    @SerialName("horario") val horario: String = "",
    @SerialName("cupo_maximo") val cupo_maximo: String = "0.0",
    @SerialName("imagen") val imagen:String = ""
){
}