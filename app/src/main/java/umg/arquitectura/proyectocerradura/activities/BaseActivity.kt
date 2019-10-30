package umg.arquitectura.proyectocerradura.activities

import android.os.Bundle
import android.os.StrictMode
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
    }

    fun getBiometricID() : String = Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)

    fun showAlert(title : String, description: String, onAccept : () -> Unit){
        alert( description, title) {
            yesButton { onAccept() }
        }.show()
    }
}