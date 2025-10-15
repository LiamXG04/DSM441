package com.example.guia8



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class AdaptadorPersona(
    private var lista: MutableList<Persona>,
    private val listener: OnPersonaAction
) : RecyclerView.Adapter<AdaptadorPersona.PersonaVH>() {
    interface OnPersonaAction {
        fun onEditar(persona: Persona)
        fun onEliminar(persona: Persona)
    }
    inner class PersonaVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val tvDui: TextView = itemView.findViewById(R.id.tvDui)
        val btnEditar: ImageButton = itemView.findViewById(R.id.btnEditar)
        val btnEliminar: ImageButton = itemView.findViewById(R.id.btnEliminar)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            PersonaVH {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_persona, parent, false)
        return PersonaVH(vista)
    }
    override fun onBindViewHolder(holder: PersonaVH, position: Int) {
        val persona = lista[position]
        holder.tvNombre.text = persona.nombre
        holder.tvDui.text = "DUI: ${persona.dui}"
        holder.btnEditar.setOnClickListener { listener.onEditar(persona) }
        holder.btnEliminar.setOnClickListener { listener.onEliminar(persona) }
    }
    override fun getItemCount(): Int = lista.size
    fun setData(nuevaLista: List<Persona>) {
        lista.clear()
        lista.addAll(nuevaLista)
        notifyDataSetChanged()
    }
}