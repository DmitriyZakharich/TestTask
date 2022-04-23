package com.example.testtasktutu.screens.list_screen.domain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testtasktutu.R
import com.example.testtasktutu.screens.list_screen.domain.models.RepositoryBriefInfoDomain

class CustomRecyclerAdapter(
    private val data: List<RepositoryBriefInfoDomain>,
    private val clickListener: (RepositoryBriefInfoDomain) -> Unit
) : RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView) { position -> clickListener(data[position]) }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.repositoryName.text = data[position].name
        holder.description.text = data[position].description
    }

    override fun getItemCount() = data.size

    class MyViewHolder(itemView: View, clickAtPosition: (Int) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        val repositoryName: TextView = itemView.findViewById(R.id.repository_name)
        val description: TextView = itemView.findViewById(R.id.description)

        init {
            itemView.setOnClickListener {
                clickAtPosition(bindingAdapterPosition)
            }
        }
    }
}