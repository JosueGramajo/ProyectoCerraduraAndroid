package umg.arquitectura.proyectocerradura.activities

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_logs.*
import umg.arquitectura.proyectocerradura.R
import umg.arquitectura.proyectocerradura.adapters.LogsAdapter
import umg.arquitectura.proyectocerradura.objects.LogData
import umg.arquitectura.proyectocerradura.retrofit.BackendServices

class LogsActivity : BaseActivity() {

    var logList = arrayListOf<LogData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logs)

        logList = BackendServices.getLogs()

        recycler_logs.layoutManager = GridLayoutManager(this, 1)
        recycler_logs.addItemDecoration(DividerItemDecoration(recycler_logs.context, 1))
        recycler_logs.adapter = LogsAdapter(logList)
    }

}