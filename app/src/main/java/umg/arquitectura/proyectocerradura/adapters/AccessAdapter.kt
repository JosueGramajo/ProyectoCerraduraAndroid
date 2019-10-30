package umg.arquitectura.proyectocerradura.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import umg.arquitectura.proyectocerradura.R
import umg.arquitectura.proyectocerradura.objects.LocalUser

class AccessAdapter(val list: List<LocalUser>, val onAcceptance : (LocalUser) -> Unit, val onRejection : (LocalUser) -> Unit)
    : RecyclerView.Adapter<AccessAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_access, parent,false)
        return ViewHolder(view, onAcceptance, onRejection)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list.get(position))

    override fun getItemCount(): Int = list.count()

    class ViewHolder(itemView : View, val onAccept : (LocalUser) -> Unit, val onRejection : (LocalUser) -> Unit) : RecyclerView.ViewHolder(itemView){
        fun bind(user : LocalUser) = with(itemView){
            val tv_name = itemView.findViewById<TextView>(R.id.tv_access_name)
            val accept_button = itemView.findViewById<Button>(R.id.button_accept_request)
            val reject_button = itemView.findViewById<Button>(R.id.button_reject_request)

            tv_name.text = user.name

            accept_button.setOnClickListener { onAccept(user) }

            reject_button.setOnClickListener { onRejection(user) }
        }
    }
}