package com.example.guia05

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class DishAdapter(
    private val items: List<Dish>,
    private val onClick: (Dish) -> Unit
) : RecyclerView.Adapter<DishAdapter.DishViewHolder>() {
    inner class DishViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val ivDish: ImageView = itemView.findViewById(R.id.ivDish)
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            DishViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dish, parent, false)
        return DishViewHolder(view)
    }
    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val dish = items[position]
        holder.tvName.text = dish.name
        holder.tvPrice.text = dish.price
        val ctx = holder.itemView.context
        val resId = ctx.resources.getIdentifier(dish.imageName, "drawable",
            ctx.packageName)
        if (resId != 0) holder.ivDish.setImageResource(resId)
        else
            holder.ivDish.setImageResource(R.drawable.ic_launcher_foreground)
        holder.itemView.setOnClickListener { onClick(dish) }
    }
    override fun getItemCount(): Int = items.size
}