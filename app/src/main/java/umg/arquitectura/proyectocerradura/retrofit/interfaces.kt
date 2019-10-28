package umg.arquitectura.proyectocerradura.retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import okhttp3.ResponseBody
import retrofit2.Call


interface ServoRequest{
    @GET("arduino/servo/{servo_pin}/{rotation_degree}")

    fun getMovieDatils(
        @Path("servo_pin") servoPin: Int,
        @Path("rotation_degree") rotationDegree : Int
    ) : Call<ResponseBody>
}