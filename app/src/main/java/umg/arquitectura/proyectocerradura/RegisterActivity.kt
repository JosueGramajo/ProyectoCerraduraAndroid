package umg.arquitectura.proyectocerradura

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast
import umg.arquitectura.proyectocerradura.fingerprint.FingerprintAuthentication
import umg.arquitectura.proyectocerradura.utils.SharedPreferences

class RegisterActivity : AppCompatActivity() {

    var fingerprintRecognized = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        if (fingerprintRecognized){

        }else{

        }

        button_request_register.setOnClickListener {
            val name = tv_name.text
            val id = Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)

            sendRequest()
        }

        button_enroll_fingerprint.setOnClickListener {
            startActivityForResult<FingerprintAuthentication>(300)
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
                    toast("Huella enrolada exitosamente")
                    fingerprintRecognized = true
                }
            }
        }
    }
}