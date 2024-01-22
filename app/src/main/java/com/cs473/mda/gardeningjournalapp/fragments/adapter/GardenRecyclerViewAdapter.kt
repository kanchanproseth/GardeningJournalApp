package com.cs473.mda.gardeningjournalapp.fragments.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cs473.mda.gardeningjournalapp.R
import com.cs473.mda.gardeningjournalapp.entity.Plant

class GardenRecyclerViewAdapter(val plants: List<Plant>, var onItemClick: ((Int) -> Unit)? = null): RecyclerView.Adapter<GardenRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.plantNameTextView)
        init {
            itemView.setOnClickListener {
                val id = plants[adapterPosition].id
                onItemClick?.invoke(id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_plant, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return plants.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plant = plants[position]
        holder.nameTextView.text = plant.name

    }

}