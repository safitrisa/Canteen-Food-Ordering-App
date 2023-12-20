package com.example.canteenfoodorderingapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MinumAdapter(private val data: ArrayList<ItemMakanMinum>): RecyclerView.Adapter<MinumAdapter.MakanMinumViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MakanMinumViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_minum, parent, false)
        return MakanMinumViewHolder(itemView)
    }
    override fun getItemCount(): Int {
        return data.size
    }
    override fun onBindViewHolder(holder: MakanMinumViewHolder, position: Int) {
        val currentItem = data[position]

        holder.nama.text = currentItem.nama
        holder.namaToko.text = currentItem.namaToko
        holder.stok.text = currentItem.stok
        holder.harga.text = currentItem.harga
    }
    class MakanMinumViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val nama    : TextView = itemView.findViewById(R.id.textView9)
        val namaToko    : TextView = itemView.findViewById(R.id.textView11)
        val stok    : TextView = itemView.findViewById(R.id.textView12)
        val harga   : TextView = itemView.findViewById(R.id.textView13)
    }
}