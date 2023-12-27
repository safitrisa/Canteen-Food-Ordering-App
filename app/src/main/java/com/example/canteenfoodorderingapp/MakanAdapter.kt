package com.example.canteenfoodorderingapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase

class MakanAdapter(private val data: ArrayList<ItemMakanMinum>): RecyclerView.Adapter<MakanAdapter.MakanMinumViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MakanMinumViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_makan, parent, false)
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

        holder.itemView.setOnClickListener {
            showOrderDialog(holder.itemView.context, position)
        }
    }
    class MakanMinumViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val nama    : TextView = itemView.findViewById(R.id.textView9)
        val namaToko    : TextView = itemView.findViewById(R.id.textView11)
        val stok    : TextView = itemView.findViewById(R.id.textView12)
        val harga   : TextView = itemView.findViewById(R.id.textView13)
    }
    private fun showOrderDialog(context: Context, position: Int) {
        // Inflate the custom layout
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_order, null)
        val editTextQuantity = dialogView.findViewById<EditText>(R.id.editTextQuantity)

        val builder = AlertDialog.Builder(context)
//        builder.setTitle("Order Item")
//        builder.setMessage("Order item with ID: $position")

        // Set the custom view
        builder.setView(dialogView)

        builder.setPositiveButton("Order") { dialog, which ->
            val quantity = editTextQuantity.text.toString()
            if (quantity.isNotEmpty()) {
                // Get a reference to the database
                val database = FirebaseDatabase.getInstance()
                val ordersRef = database.getReference("dataPesanan")
                val dataPesananDipilih = data[position]
                val namaPesananDipilih = dataPesananDipilih.nama


                // Create a unique key for the new order
                val orderId = ordersRef.push().key ?: return@setPositiveButton

                // Create an order object. Assuming you have a data class Order
                val order = pesanan(orderId, namaPesananDipilih, quantity.toInt(), status = "Diproses")

                // Push the data to Firebase
                ordersRef.child(orderId).setValue(order)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Pesanan diterima", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(context, "Pesanan Anda gagal dibuat", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(context, "Pesanan Anda gagal dibuat", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Cancel", null)
        val dialog = builder.create()
        dialog.show()
    }

}