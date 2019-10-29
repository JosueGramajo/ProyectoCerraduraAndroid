package umg.arquitectura.proyectocerradura.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient



object ArduinoServices{
    private const val BASE_URL = "http://192.168.43.103/"

    var okHttpClient = OkHttpClient.Builder()
        .connectionSpecs(listOf(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT))
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL).client(okHttpClient)
        .build()

    fun moveServo(rotationDegree : Int){
        val service = retrofit.create(ArduinoRequests::class.java)

        val call = service.getMovieDatils(9, rotationDegree)

        val response = call.execute()
    }
}