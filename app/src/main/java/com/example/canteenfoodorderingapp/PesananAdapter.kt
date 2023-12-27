package com.example.canteenfoodorderingapp

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class PesananAdapter(private val data: ArrayList<pesanan>) :
    RecyclerView.Adapter<PesananAdapter.PesananViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PesananViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_pesanan, parent, false)
        return PesananViewHolder(itemView)
    }

    override fun getItemCount(): Int = data.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: PesananViewHolder, position: Int) {
        val currentItem = data[position]

        with(holder) {
            namaMakanan.text = currentItem.itemId
            jumlah.text = currentItem.quantity.toString()
            status.text = currentItem.status

            itemView.setOnClickListener {
                showOrderDialog(itemView.context, position)
            }
        }
    }

    class PesananViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val namaMakanan: TextView = itemView.findViewById(R.id.textView9)
        val jumlah: TextView = itemView.findViewById(R.id.textView11)
        val status: TextView = itemView.findViewById(R.id.textView13)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showOrderDialog(context: Context, position: Int) {
        AlertDialog.Builder(context)
            .setPositiveButton("Pesanan Diterima") { _, _ ->
                val database = FirebaseDatabase.getInstance()
                val ordersRef = database.getReference("dataRiwayat")
                val databasePesanan = database.getReference("dataPesanan")
                val dataPesananDipilih = data[position]
                val namaPesananDipilih = dataPesananDipilih.itemId
                val quantity = dataPesananDipilih.quantity
                val localDate = LocalDate.now()
                val formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy")
                val formattedString = localDate.format(formatter)
                val orderId = ordersRef.push().key ?: return@setPositiveButton
                val invoice = "INV/$formattedString/CFOA/$orderId".replace(" ", "")

                val order = riwayat(orderId, namaPesananDipilih, quantity.toInt(), invoice)

                val orderIdToRemove = dataPesananDipilih.id

                ordersRef.child(orderId).setValue(order)
                    .addOnSuccessListener {
                        databasePesanan.child(orderIdToRemove).removeValue()
                        Toast.makeText(context, "Pesanan Selesai", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            context,
                            "Pesanan Anda gagal dibuat",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }
}