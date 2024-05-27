package com.example.testtasktutu.presentation.list_screen.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.domain.models.UserShort
import com.example.testtasktutu.databinding.RecyclerviewItemBinding
import com.example.testtasktutu.presentation.common.KEY_LOGIN

class CustomRecyclerAdapter(
    private val data: List<UserShort>,
    private val onClick: (Bundle) -> Unit
) : RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentData = data[position]
        val bundle = Bundle().apply {
            putString(KEY_LOGIN, currentData.login)
        }

        holder.binding.apply {
            login.text = currentData.login
            userId.text = currentData.id.toString()
            avatar.load(currentData.avatarUrl)
            card.setOnClickListener { onClick(bundle) }
        }
    }

    override fun getItemCount() = data.size

    inner class MyViewHolder(val binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root)
}