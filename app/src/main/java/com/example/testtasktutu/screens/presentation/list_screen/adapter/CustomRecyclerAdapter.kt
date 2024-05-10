package com.example.testtasktutu.screens.presentation.list_screen.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.testtasktutu.R
import com.example.domain.models.UserShort

class CustomRecyclerAdapter(
    private val data: List<UserShort>,
    private val onClick: (Bundle) -> Unit
) : RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.repositoryName.text = data[position].login
        holder.userId.text = data[position].id.toString()
        holder.avatar.load(data[position].avatarUrl)

        val bundle = Bundle().apply {
            putString("login", data[position].login)
        }
        holder.itemView.setOnClickListener { onClick(bundle) }
    }

    override fun getItemCount() = data.size

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val repositoryName: TextView = itemView.findViewById(R.id.login)
        val userId: TextView = itemView.findViewById(R.id.user_id)
        val avatar: ImageView = itemView.findViewById(R.id.avatar)
    }
}