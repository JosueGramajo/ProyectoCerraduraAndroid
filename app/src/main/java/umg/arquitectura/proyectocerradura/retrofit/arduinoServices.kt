package umg.arquitectura.proyectocerradura.retrofit

import com.fasterxml.jackson.databind.ObjectMapper
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import umg.arquitectura.proyectocerradura.objects.LogData
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


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

        val call = service.rotateServo(9, rotationDegree)

        val response = call.execute()
    }

    fun setLogData(name : String, status : String){
        val service = retrofit.create(ArduinoRequests::class.java)

        val current = LocalDateTime.now()

        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = current.format(dateFormatter)

        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS")
        val time = current.format(timeFormatter)

        val logObj = LogData(name, time, date, status)
        val mapper = ObjectMapper()

        var strJson = mapper.writeValueAsString(logObj)
        strJson = strJson.replace("{", "**")
        strJson = strJson.replace("}", "*")
        strJson = strJson.replace(",", "_")

        val call = service.setJsonData(strJson)

        val response = call.execute()

        println(">>>>> ${response.body()!!.string()}")
    }
}