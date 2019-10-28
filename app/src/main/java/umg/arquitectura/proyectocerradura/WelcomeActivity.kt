package umg.arquitectura.proyectocerradura

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_welcome.*
import org.jetbrains.anko.startActivity
import umg.arquitectura.proyectocerradura.utils.SharedPreferences

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        if(SharedPreferences.getSharedPreference(SharedPreferences.enrolledUser, this).isNotEmpty()){
            startActivity<MainActivity>()
        }

        register_button.setOnClickListener {
            startActivity<RegisterActivity>()
        }
    }
}