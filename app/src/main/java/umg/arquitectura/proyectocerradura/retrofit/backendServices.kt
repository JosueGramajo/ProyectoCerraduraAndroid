package umg.arquitectura.proyectocerradura.retrofit

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.MediaType
import okhttp3.RequestBody
import umg.arquitectura.proyectocerradura.objects.*
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

    fun getPendingUsers() : ArrayList<LocalUser>{
        val service = retrofit.create(BackendRequests::class.java)

        val call = service.getPendingUsers()

        val response = call.execute()

        val mapper = ObjectMapper()

        val listObj = mapper.readValue<UserList>(response.body().string())

        val userList = arrayListOf<LocalUser>()

        listObj.list.map { userList.add(it) }

        return userList
    }

    fun changeUserState(biometricID : String, state : String) : String{
        val service = retrofit.create(BackendRequests::class.java)

        val mapper = ObjectMapper()

        val stateReq = ChangeState(biometricID, state)

        val body = RequestBody.create(
            MediaType.parse("application/json; charset=utf-8"),
            mapper.writeValueAsString(stateReq)
        )

        val call = service.changeUserState(body)

        try{
            val response = call.execute()
            return response.body().string()
        }catch (se : SocketTimeoutException){
            return "Ocurrio un error"
        }
    }

    fun getLogs() : ArrayList<LogData>{
        val service = retrofit.create(BackendRequests::class.java)

        val call = service.getLogs()

        val response = call.execute()

        val mapper = ObjectMapper()

        val listObj =  mapper.readValue<LogList>(response.body().string())

        return listObj.list
    }
}