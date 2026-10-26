package com.example.app_cursos.InerfaceApi
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    //devuelve todos los cursos disponibles
    @GET("api_cursos.php")
    suspend fun obtenerCursos()

    //devuelve el detalle de un curso como nombre, carrera, salon,etc
    @GET("api_detalle_curso.php")
    suspend fun obtenerDetalleCurso()

    //mediante un id el alumno se inscribe a un curso si hay cupo
    @POST("inscripcion_curso.php")
    suspend fun inscribirseCurso()
    //obtiene el estado de los cursos y sirve en la vista estados para ver los cursos y el cupo
    @GET("obtener_estado_cursos.php")
    suspend fun obtenerEstadocursos()

}