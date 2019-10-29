package umg.arquitectura.proyectocerradura.fingerprint

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import umg.arquitectura.proyectocerradura.R
import java.util.concurrent.Executors

class FingerprintAuthentication : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            R.layout.activity_fingerprint
        )

        val executor = Executors.newSingleThreadExecutor()
        val activity: FragmentActivity = this // reference to activity
        val biometricPrompt = BiometricPrompt(activity, executor, object : BiometricPrompt.AuthenticationCallback() {

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)

                val returnIntent = Intent()
                returnIntent.putExtra("result", errString.toString())

                if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                    setResult(400, returnIntent)
                } else {
                    setResult(400, returnIntent)
                }

                finish()
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                setResult(500)
                finish()

            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()

                /*val returnIntent = Intent()
                returnIntent.putExtra("result", "Autenticacion fallida")
                setResult(400, returnIntent)
                finish()*/

            }
        })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Autenticación biométrica")
            .setSubtitle("")
            .setDescription("Coloque su huella digital enrolada en el dispositivo")
            .setNegativeButtonText("Cancelar")
            .build()


        biometricPrompt.authenticate(promptInfo)

    }

}