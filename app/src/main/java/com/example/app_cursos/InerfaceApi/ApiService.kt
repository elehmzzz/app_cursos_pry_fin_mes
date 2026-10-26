package com.example.app_cursos.InerfaceApi
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("api_cursos.php") //
    suspend fun obtenerCursos()

    @GET("api_obtenerInscripciones.php")
    suspend fun obtenerInscripciones()

    @GET("api_detalle_curso.php")
    suspend fun obtenerDetalleCurso()

    @POST("api_registrarInscripcion.php")
    suspend fun inscribirseCurso()

}