package umg.arquitectura.proyectocerradura.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import umg.arquitectura.proyectocerradura.retrofit.ArduinoServices
import umg.arquitectura.proyectocerradura.R
import umg.arquitectura.proyectocerradura.fingerprint.FingerprintAuthentication
import umg.arquitectura.proyectocerradura.retrofit.BackendServices
import androidx.core.content.res.TypedArrayUtils.getResourceId
import android.content.res.TypedArray
import android.graphics.Color
import org.jetbrains.anko.*


class MainActivity : BaseActivity() {

    var authorized = false

    var currentUser = ""
    var currentState = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //checkUserCurrentState()


        ln_open.setOnClickListener {
            startActivityForResult<FingerprintAuthentication>(300)
        }

        ln_close.setOnClickListener {
            closeDoor()
        }

        ln_pending.setOnClickListener {
            startActivity<AccessManagementActivity>()
        }

        ln_statistics.setOnClickListener {
            startActivity<LogsActivity>()
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
        }else if(role.user_state.equals("pending")){
            image_authorization.setImageResource(R.drawable.pending_icon)
            tv_authorization.text = "La solicitud de acceso aun ha sido aceptada por el adminsitrador"
            authorized = false
        }
    }

    fun openDoor(){
        if(currentState.equals("unauthorized")){
            showAlert("Error", "Este usuario no se encuentra autorizado para abrir esta puerta") { }
        }else if(currentState.equals("pending")){
            showAlert("Error", "La solicitud de acceso aun ha sido aceptada por el adminsitrador") { }
        }else{
            ArduinoServices.setLogData(currentUser, currentState)

            ArduinoServices.moveServo(90)
        }

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
