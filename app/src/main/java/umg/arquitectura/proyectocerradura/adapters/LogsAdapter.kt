package umg.arquitectura.proyectocerradura.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import umg.arquitectura.proyectocerradura.R
import umg.arquitectura.proyectocerradura.objects.LogData

class LogsAdapter(val list: List<LogData>)
    : RecyclerView.Adapter<LogsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_log, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list.get(position))

    override fun getItemCount(): Int = list.count()

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(log : LogData) = with(itemView){
            val tv_name = itemView.findViewById<TextView>(R.id.tv_log_name)
            tv_name.text = log.name

            val tv_status = itemView.findViewById<TextView>(R.id.tv_log_status)
            tv_status.text = log.status

            val tv_date = itemView.findViewById<TextView>(R.id.tv_log_date)
            tv_date.text = log.date

            val tv_time = itemView.findViewById<TextView>(R.id.tv_log_time)
            tv_time.text = log.time
        }
    }
}