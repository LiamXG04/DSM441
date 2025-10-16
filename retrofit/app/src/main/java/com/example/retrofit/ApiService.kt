package com.example.retrofit

// Importaciones necesarias de Retrofit para trabajar con llamadas HTTP y anotaciones de métodos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
// Definición de la interfaz ApiService, que define los endpoints de la API REST
interface ApiService {
    // Método para obtener imágenes de perros por raza
    // @GET indica que es una solicitud HTTP GET
    // @Path se utiliza para agregar el valor de la variable raza a la URL
    @GET("{raza}/images")
    fun getDogsByBreed(@Path("raza") raza: String?): Call<DogsResponse?>?
}