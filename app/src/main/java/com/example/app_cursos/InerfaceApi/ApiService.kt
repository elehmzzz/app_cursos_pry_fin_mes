package com.example.app_cursos.InerfaceApi
import android.view.PixelCopy.request
import androidx.core.view.DragAndDropPermissionsCompat.request
import com.example.app_cursos.modelo.clsDetalleCursoResponse
import com.example.app_cursos.modelo.clsEstadoCursosResponse
import com.example.app_cursos.modelo.clsInscripcionRequest
import com.example.app_cursos.modelo.clsInscripcionResponse
import com.example.app_cursos.modelo.clsLoginRequest
import com.example.app_cursos.modelo.clsLoginResponse
import com.example.app_cursos.modelo.dataCategorias
import com.example.app_cursos.modelo.clsCarrerasModelos
import com.example.app_cursos.modelo.dataCursos
import com.example.app_cursos.modelo.clsRegistroRequest
import com.example.app_cursos.modelo.clsRegistroResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Query

interface ApiService {
    //CODIGO PENDIENTE
    //devuelve todos los cursos disponibles
    @GET("api_cursos.php")
    suspend fun obtenerCursos(): List<dataCursos>

    //devuelve las carreras disponibles
    @GET("Api_obtener_categoria_carrera.php")
    suspend fun obtenerCarreras(): List<clsCarrerasModelos>

    //devuelve el detalle de un curso como nombre, carrera, salon,etc
    @GET("api_detalle_curso.php")
    suspend fun obtenerDetalleCurso(@Query("id_curso") idCurso: Int): clsDetalleCursoResponse

    //obtiene el estado de los cursos y sirve en la vista estados para ver los cursos y el cupo

    @GET("obtener_estado_cursos.php")
    suspend fun obtenerEstadocursos(): clsEstadoCursosResponse
    //mediante un id el alumno se inscribe a un curso si hay cupo
    @POST("inscripcion_curso.php")
    suspend fun inscribirseCurso(@Body request: clsInscripcionRequest): clsInscripcionResponse

    @POST("login.php")
    suspend fun iniciarSesion(@Body request: clsLoginRequest): clsLoginResponse

    @POST("registro_alumno.php")
    suspend fun registrarUsuario(@Body request: clsRegistroRequest): clsRegistroResponse

}