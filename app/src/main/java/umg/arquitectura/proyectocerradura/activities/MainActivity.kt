package umg.arquitectura.proyectocerradura.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import umg.arquitectura.proyectocerradura.retrofit.ArduinoServices
import org.jetbrains.anko.startActivityForResult
import umg.arquitectura.proyectocerradura.R
import umg.arquitectura.proyectocerradura.fingerprint.FingerprintAuthentication
import umg.arquitectura.proyectocerradura.retrofit.BackendServices


class MainActivity : BaseActivity() {

    var authorized = false

    var currentUser = ""
    var currentState = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkUserCurrentState()

        ln_open.setOnClickListener {
            startActivityForResult<FingerprintAuthentication>(300)
        }

        ln_close.setOnClickListener {
            closeDoor()
        }
    }

    fun checkUserCurrentState(){
        val id = getBiometricID()
        val role = BackendServices.getRoleAndState(id)

        currentUser = role.name
        currentState = role.user_state

        if (role.id == 2){
            admin_container.visibility = View.INVISIBLE
        }else if(role.id == 1){
            admin_container.visibility = View.VISIBLE
        }

        if (role.user_state.equals("unauthorized")){
            image_authorization.setImageResource(R.drawable.unauthorized_icon)
            tv_authorization.text = "Este usuario no se encuentra autorizado para abrir la puerta, comuniquese con el administrador"
            authorized = false
        }else if(role.user_state.equals("authorized")){
            image_authorization.setImageResource(R.drawable.authorized_icon)
            tv_authorization.text = "Este usuario se encuentra autorizado para abrir la puerta"
            authorized = true
        }
    }

    fun openDoor(){
        ArduinoServices.setLogData(currentUser, currentState)

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                checkUserCurrentState()
            }
            else -> super.onOptionsItemSelected(item)
        }

        return true
    }
}
