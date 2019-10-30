package umg.arquitectura.proyectocerradura.activities

import android.os.Bundle
import umg.arquitectura.proyectocerradura.objects.LogData
import umg.arquitectura.proyectocerradura.retrofit.BackendServices

class LogsActivity : BaseActivity() {

    var logList = arrayListOf<LogData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        logList = BackendServices.getLogs()

        //TODO: recycler and filters
    }

}