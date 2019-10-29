package umg.arquitectura.proyectocerradura.activities

import android.os.Bundle
import android.os.StrictMode
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
    }

    fun getBiometricID() : String = Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)
}