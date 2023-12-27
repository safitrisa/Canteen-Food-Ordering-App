package com.example.canteenfoodorderingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class StatusPembelian : Fragment() {
    private lateinit var dbref: DatabaseReference
    private lateinit var recyclerView: RecyclerView
    private lateinit var pesananList: ArrayList<pesanan> // Assuming Pesanan is the correct data class

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_status_pembelian, container, false)
        recyclerView = root.findViewById(R.id.pesananList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        pesananList = arrayListOf()
        getBarangData()
        return root
    }

    private fun getBarangData() {
        dbref = FirebaseDatabase.getInstance().getReference("dataPesanan")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    pesananList.clear() // Clear the list before adding new data
                    for (barangSnapshot in snapshot.children) {
                        val pesanan = barangSnapshot.getValue(pesanan::class.java)
                        pesananList.add(pesanan!!)
                    }

                    recyclerView.adapter = PesananAdapter(pesananList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle onCancelled event if needed
            }
        })
    }
}
