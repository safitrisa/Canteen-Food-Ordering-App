package com.example.canteenfoodorderingapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MakanMinumViewHolder(infalter: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(infalter.inflate(R.layout.item_makan_minum,parent,false)){
    private var textNama    : TextView? = null
    private var textToko    : TextView? = null
    private var textStok    : TextView? = null
    private var textHarga   : TextView? = null
    init {
        textNama    = itemView.findViewById(R.id.textView9)
        textToko    = itemView.findViewById(R.id.textView11)
        textStok    = itemView.findViewById(R.id.textView12)
        textHarga   = itemView.findViewById(R.id.textView13)
    }
    fun bind(data: ItemMakanMinum){
        textNama?.setText(data.nama)
        textToko?.setText(data.namaToko)
        textStok?.setText(data.stok)
        textHarga?.setText(data.harga)
    }
}