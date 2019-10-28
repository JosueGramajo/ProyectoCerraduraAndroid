package umg.arquitectura.proyectocerradura.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.ConnectionSpec
import javax.xml.datatype.DatatypeConstants.SECONDS
import okhttp3.OkHttpClient



object RetrofitServices{
    //private const val BASE_URL = "http://192.168.43.103/"

    private const val BASE_URL = "http://192.168.2.1:9901/"

    var okHttpClient = OkHttpClient.Builder()
        .connectionSpecs(listOf(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT))
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL).client(okHttpClient)
        .build()

    fun moveServo(rotationDegree : Int) : Pair<Int, String>{
        val service = retrofit.create(ServoRequest::class.java)



        val call = service.getMovieDatils(9, rotationDegree)

        val response = call.execute()

        val responseBody = response!!.raw().body().toString()

        val status = response.code()

        return Pair(status, responseBody)
    }
}