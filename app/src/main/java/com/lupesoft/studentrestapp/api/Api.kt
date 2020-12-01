package com.lupesoft.studentrestapp.api

import com.lupesoft.studentrestapp.api.dto.StudentDto
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface Api {

    @GET("estudiante")
    suspend fun getStudents(): Response<List<StudentDto>>

    @GET("estudiante/{id}")
    suspend fun getStudent(
        @Path("id") id: Int,
    ): Response<StudentDto>

    @POST("estudiante/{id}")
    suspend fun postStudent(
        @Path("id") id: Int,
        @Body student: StudentDto
    ): Response<ResponseBody>

    @PUT("estudiante/{id}")
    suspend fun putStudent(
        @Path("id") id: Int,
        @Body device: StudentDto
    ): Response<ResponseBody>

    @DELETE("estudiante/{id}")
    suspend fun deleteStudent(
        @Path("id") id: Int
    ): Response<ResponseBody>

    companion object {
        private const val BASE_URL = "http://localhost:8080/exposicionSD-1.0/webresources/"

        fun create(): Api {
            val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        }

    }

}


/*

Integer idEstudiante;
String nombreEstudiante;
BigInteger codigoEstudiante;

req: crear estudiante
method: post
body:
{
    "nombreEstudiante":"Jhon Mendoza",
    "codigoEstudiante":20202678000
}

si se crea responde un status 200

url: http://localhost:8080/exposicionSD-1.0/webresources/estudiante

req: editar estudiante
method: put
body:
{
    "idEstudiante":1,
    "nombreEstudiante":"Jhon Mendoza",
    "codigoEstudiante":20202678000
}
si se edita responde un status 204
url: http://localhost:8080/exposicionSD-1.0/webresources/estudiante/{id} osea 1 o el que sea

req: eliminar un estudiante
method: delete
body: no hay
si se elimina responde un status 204
url: http://localhost:8080/exposicionSD-1.0/webresources/estudiante/{id} osea 1 o el que sea

req: obtener un estudiante
method: get
body: no hay
si se obtiene responde un status 200 y manda un cuerpo como este:
{
    "idEstudiante": 1,
    "nombreEstudiante": "Jhon Mendoza",
    "codigoEstudiante": 20202678001
}
url: http://localhost:8080/exposicionSD-1.0/webresources/estudiante/{id} osea 1 o el que sea


req: obtener todos los estudiantes
method: get
body: no hay
si se obtiene responde un status 200 y manda un cuerpo como este:
[
    {
        "idEstudiante": 1,
        "nombreEstudiante": "Jhon Mendoza",
        "codigoEstudiante": 20202678001
    },
    {
        "idEstudiante": 2,
        "nombreEstudiante": "Camilo Gamba",
        "codigoEstudiante": 20202678004
    }
]
url: http://localhost:8080/exposicionSD-1.0/webresources/estudiante



 */