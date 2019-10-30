package umg.arquitectura.proyectocerradura.activities

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_access_management.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.cancelButton
import org.jetbrains.anko.yesButton
import umg.arquitectura.proyectocerradura.adapters.AccessAdapter
import umg.arquitectura.proyectocerradura.objects.LocalUser
import umg.arquitectura.proyectocerradura.retrofit.BackendServices

class AccessManagementActivity : BaseActivity() {

    var pendingUserList = arrayListOf<LocalUser>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pendingUserList = BackendServices.getPendingUsers()

        recycler_status.layoutManager = GridLayoutManager(this, 1)
        recycler_status.adapter = AccessAdapter(pendingUserList, onAcceptance =  { user ->



            alert( "Desea aceptar la solicitud de ${user.name}?", "") {
                yesButton {
                    val response = BackendServices.changeUserState(user.biometricID, "authorized")

                    showAlert("", response) { finish() }
                }
                cancelButton {  }
            }.show()



        }, onRejection = { user ->


            alert( "Desea rechazar la solicitud de ${user.name}?", "") {
                yesButton {
                    val response = BackendServices.changeUserState(user.biometricID, "unauthorized")

                    showAlert("", response) { finish() }
                }
                cancelButton {  }
            }.show()


        })
    }

}