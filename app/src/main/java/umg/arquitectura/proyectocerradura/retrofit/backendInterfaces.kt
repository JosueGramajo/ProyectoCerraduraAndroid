package umg.arquitectura.proyectocerradura.retrofit

import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface BackendRequests{
    @POST("/registerUser")
    fun registerUser(
        @Body body : RequestBody
    ) : Call<ResponseBody>

    @POST("/getRoleAndState")
    fun getRoleAndState(
        @Body body : RequestBody
    ) : Call<ResponseBody>

    @POST("/getPendingUsers")
    fun getPendingUsers() : Call<ResponseBody>

    @POST("/changeUserState")
    fun changeUserState(
        @Body body : RequestBody
    ) : Call<ResponseBody>

    @POST("/getLogs")
    fun getLogs() : Call<ResponseBody>
}