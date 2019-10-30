package umg.arquitectura.proyectocerradura.retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call


interface ArduinoRequests{
    @GET("arduino/servo/{servo_pin}/{rotation_degree}")
    fun rotateServo(
        @Path("servo_pin") servoPin: Int,
        @Path("rotation_degree") rotationDegree : Int
    ) : Call<ResponseBody>

    @GET("data/put/currentLog/{json_data}")
    fun setJsonData(
        @Path("json_data") jsonData : String
    ) : Call<ResponseBody>
}