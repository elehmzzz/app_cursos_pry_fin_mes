package com.example.app_cursos.modelo
import kotlinx.serialization.Serializable

@Serializable
data class clsCurso(val id_curso: Int, val codigo: String, val nombre: String, val descripcion: String, val espacio: String, val salon: String, val total_horas: Int, val dias_atencion: String, val horario: String, val cupo_maximo: Int, val imagen: String?, val id_carrera: Int) {
}