package com.example.canteenfoodorderingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.*

class StatusPembelian : Fragment() {
    private lateinit var dbref: Query
    private lateinit var recyclerView: RecyclerView
    private lateinit var pesananList: ArrayList<pesanan> // Renamed to Pesanan (PascalCase)

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
        val uid = Firebase.auth.currentUser?.uid
        dbref = FirebaseDatabase.getInstance().getReference("dataPesanan").orderByChild("idBuyer").equalTo(uid)

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    pesananList.clear() // Clear the list before adding new data
                    for (barangSnapshot in snapshot.children) {
                        val pesanan = barangSnapshot.getValue(pesanan::class.java)
                        pesanan?.let { pesananList.add(it) }
                    }

                    val adapter = recyclerView.adapter as? PesananAdapter
                    adapter?.notifyDataSetChanged() ?: run {
                        recyclerView.adapter = PesananAdapter(pesananList)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Log the error or show a Toast message
            }
        })
    }
}
