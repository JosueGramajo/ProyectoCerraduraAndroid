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

        checkScreens()

        register_button.setOnClickListener {
            startActivity<RegisterActivity>()
        }
    }

    override fun onResume() {
        super.onResume()

        checkScreens()
    }

    fun checkScreens(){
        val k = SharedPreferences.getSharedPreference(SharedPreferences.enrolledUser, this)

        if(k.isNotEmpty()){
            startActivity<MainActivity>()
        }
    }
}