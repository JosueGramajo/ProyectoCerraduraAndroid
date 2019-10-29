package umg.arquitectura.proyectocerradura.retrofit

import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface BackendRequests{
    @POST("/registerUser")
    fun registerUser(
        @Body body : RequestBody
    ) : Call<ResponseBody>

    @POST("/getRoleAndState")
    fun getRoleAndState(
        @Body body : RequestBody
    ) : Call<ResponseBody>
}