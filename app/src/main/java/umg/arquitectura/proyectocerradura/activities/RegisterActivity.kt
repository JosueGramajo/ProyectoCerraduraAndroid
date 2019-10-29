package umg.arquitectura.proyectocerradura.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.*
import umg.arquitectura.proyectocerradura.R
import umg.arquitectura.proyectocerradura.fingerprint.FingerprintAuthentication
import umg.arquitectura.proyectocerradura.objects.User
import umg.arquitectura.proyectocerradura.retrofit.BackendServices
import umg.arquitectura.proyectocerradura.utils.SharedPreferences

class RegisterActivity : BaseActivity() {

    var fingerprintEnroled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        button_request_register.setOnClickListener {
            if (tv_name.text.isNullOrBlank()){
                showAlert("Error", "Debe ingresar un nombre"){ }
            }else if (!fingerprintEnroled){
                showAlert("Error", "Debe enrolar una huella digital"){}
            }else{
                val name = tv_name.text.toString()

                sendRequest(name)
            }
        }

        button_enroll_fingerprint.setOnClickListener {
            startActivityForResult<FingerprintAuthentication>(300)
        }
    }

    fun sendRequest(name : String){
        val id = getBiometricID()

        val response = BackendServices.sendUser(User(name, id))

        showAlert("", response){
            SharedPreferences.writeSharedPreference(SharedPreferences.enrolledUser, "enrolled", this)

            finish()
        }
    }

    fun showAlert(title : String, description: String, onAccept : () -> Unit){
        alert( description, title) {
            yesButton { onAccept() }
        }.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 300){
            when(resultCode){
                400 -> {
                    image_fingerprint_state.setImageResource(R.drawable.fingerprint_fail)
                    tv_enroll_info.text = "Aun no se ha enrolado ninguna huella digital"


                    showAlert("Error", data!!.getStringExtra("result")){}

                }
                500 -> {
                    fingerprintEnroled = true

                    image_fingerprint_state.setImageResource(R.drawable.fingerprint_success)
                    tv_enroll_info.text = "Huella enrolada exitosamente"
                    button_enroll_fingerprint.visibility = View.INVISIBLE
                }
            }
        }
    }
}