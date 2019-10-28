package umg.arquitectura.proyectocerradura

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import org.jetbrains.anko.startActivityForResult
import umg.arquitectura.proyectocerradura.fingerprint.FingerprintAuthentication
import umg.arquitectura.proyectocerradura.retrofit.RetrofitServices
import android.os.StrictMode
import umg.arquitectura.proyectocerradura.utils.SharedPreferences


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        login_button.setOnClickListener {
            //startActivityForResult<FingerprintAuthentication>(300)

            val response = RetrofitServices.moveServo(80)

            toast("${response.first}${response.second}")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 300){
            when(resultCode){
                400 -> {
                    val errCode = data!!.getStringExtra("result")
                    toast(errCode)
                }
                500 -> {
                    toast("success!!")
                }
            }
        }
    }
}
