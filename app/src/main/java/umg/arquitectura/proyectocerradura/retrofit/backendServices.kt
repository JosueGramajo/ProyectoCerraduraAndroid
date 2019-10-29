package umg.arquitectura.proyectocerradura.retrofit

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.MediaType
import okhttp3.RequestBody
import umg.arquitectura.proyectocerradura.objects.RoleAndState
import umg.arquitectura.proyectocerradura.objects.User
import java.net.SocketTimeoutException


object BackendServices{
    private const val BASE_URL = "http://192.168.43.52:8989/"

    val gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(BASE_URL)
        .build()

    fun sendUser(registerReq : User) : String{
        val service = retrofit.create(BackendRequests::class.java)

        val mapper = ObjectMapper()

        val body = RequestBody.create(
            MediaType.parse("application/json; charset=utf-8"),
            mapper.writeValueAsString(registerReq)
        )

        val call = service.registerUser(body)

        try{
            val response = call.execute()
            return response.body().string()
        }catch (se : SocketTimeoutException){
            return "Timeout exception"
        }

    }

    fun getRoleAndState(id : String) : RoleAndState{
        val service = retrofit.create(BackendRequests::class.java)

        val body = RequestBody.create(
            MediaType.parse("text/plain;"),
            id
        )

        val call = service.getRoleAndState(body)

        val response = call.execute()

        val json = response.body().string()

        val mapper = ObjectMapper()

        return mapper.readValue(json, RoleAndState::class.java)


    }
}