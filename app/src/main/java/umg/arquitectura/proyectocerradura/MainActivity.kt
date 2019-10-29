package umg.arquitectura.proyectocerradura

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import umg.arquitectura.proyectocerradura.retrofit.ArduinoServices
import android.os.StrictMode
import android.provider.Settings
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivityForResult
import umg.arquitectura.proyectocerradura.fingerprint.FingerprintAuthentication
import umg.arquitectura.proyectocerradura.retrofit.BackendServices


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val id = Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)
        val d = BackendServices.getRoleAndState(id)

        println(">>>>>>>>>>>>> ${d.first} - ${d.second}")

        if (d.first == 2){
            textView3.text = "GUEST"
        }else if(d.first == 1){
            textView3.text = "ADMIN"
        }

        login_button.setOnClickListener {
            startActivityForResult<FingerprintAuthentication>(300)
        }

        close_button.setOnClickListener {
            closeDoor()
        }
    }

    fun openDoor(){
        ArduinoServices.moveServo(90)
    }

    fun closeDoor(){
        ArduinoServices.moveServo(200)
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
                    openDoor()
                }
            }
        }
    }
}
