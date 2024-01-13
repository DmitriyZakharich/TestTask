package com.example.testtasktutu.screens.list_screen.presentation.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testtasktutu.R
import com.example.testtasktutu.screens.list_screen.domain.models.GithubRepoBriefInfoDomain
import com.example.testtasktutu.screens.list_screen.presentation.intent.ListIntent
import com.example.testtasktutu.screens.list_screen.presentation.viewmodel.ListViewModel

class CustomRecyclerAdapter(
    private val data: List<GithubRepoBriefInfoDomain>,
    private val onClick: (Bundle) -> Unit
    )
    : RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.repositoryName.text = data[position].name
        holder.description.text = data[position].description

        val bundle = Bundle().apply {
            putString("login", data[position].login)
            putString("name", data[position].name)
        }
        holder.itemView.setOnClickListener{onClick(bundle)}
    }

    override fun getItemCount() = data.size

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val repositoryName: TextView = itemView.findViewById(R.id.repository_name)
        val description: TextView = itemView.findViewById(R.id.description)
    }
}