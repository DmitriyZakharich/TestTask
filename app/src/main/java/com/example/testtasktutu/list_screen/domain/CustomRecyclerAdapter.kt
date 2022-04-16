package com.example.testtasktutu.list_screen.domain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testtasktutu.R

class CustomRecyclerAdapter(
    private val data: List<RepositoryInfoDomain>,
    private val clickListener: (RepositoryInfoDomain) -> Unit
) : RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView) { position -> clickListener(data[position]) }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.repositoryName.text = data[position].name
        holder.description.text = data[position].description
        holder.language.text = data[position].language
        holder.stargazersCount.text = data[position].stargazers_count.toString()
        holder.updatedAt.text = data[position].updated_at
    }

    override fun getItemCount() = data.size

    class MyViewHolder(itemView: View, clickAtPosition: (Int) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        val repositoryName: TextView = itemView.findViewById(R.id.repository_name)
        val description: TextView = itemView.findViewById(R.id.description)
        val language: TextView = itemView.findViewById(R.id.language)
        val stargazersCount: TextView = itemView.findViewById(R.id.stargazers_count)
        val updatedAt: TextView = itemView.findViewById(R.id.updated_at)

        init {
            itemView.setOnClickListener {
                clickAtPosition(bindingAdapterPosition)
            }
        }
    }
}