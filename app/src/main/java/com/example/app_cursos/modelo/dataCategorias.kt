package com.example.app_cursos.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataCategorias (
    @SerialName("id_carrera") val id: Int = 0,
    @SerialName("categoria") val categoria:String = ""
){
    override fun toString(): String {
        return categoria
    }
}
