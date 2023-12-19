package com.example.canteenfoodorderingapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MakanMinumAdapter( private val data: ArrayList<ItemMakanMinum>): RecyclerView.Adapter<MakanMinumViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MakanMinumViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MakanMinumViewHolder(inflater,parent)
    }
    override fun getItemCount(): Int {
        return data.size
    }
    override fun onBindViewHolder(holder: MakanMinumViewHolder, position: Int) {
        holder.bind(data[position])
    }
}