
package com.example.app_cursos.modelo

class clsLoginModelo {

    suspend fun iniciarSesion(
        matricula: String,
        contrasena: String
    ): clsLoginResponse {

        val solicitud = clsLoginRequest(
            matricula = matricula,
            contrasena = contrasena
        )

        return RetrofitCliente.apiService.iniciarSesion(solicitud)
    }
}
