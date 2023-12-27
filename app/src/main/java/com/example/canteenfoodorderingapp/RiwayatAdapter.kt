package com.example.canteenfoodorderingapp

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView

class RiwayatAdapter( private val data: ArrayList<riwayat>) :
RecyclerView.Adapter<RiwayatAdapter.RiwayatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiwayatViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_riwayat, parent, false)
        return RiwayatViewHolder(itemView)
    }

    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: RiwayatViewHolder, position: Int) {
        val currentItem = data[position]

        with(holder) {
            namaMakanan.text = currentItem.namaPesanan
            jumlah.text = currentItem.quantity.toString()
            invoice.text = currentItem.invoice
        }
    }

    class RiwayatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val namaMakanan: TextView = itemView.findViewById(R.id.textView9)
        val jumlah: TextView = itemView.findViewById(R.id.textView11)
        val invoice: TextView = itemView.findViewById(R.id.textView13)
    }
}