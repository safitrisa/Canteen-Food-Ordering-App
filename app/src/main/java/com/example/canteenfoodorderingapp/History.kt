package com.example.canteenfoodorderingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class History : Fragment() {
    private lateinit var dbref: Query
    private lateinit var recyclerView: RecyclerView
    private lateinit var riwayatList: ArrayList<riwayat>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_history, container, false)
        recyclerView = root.findViewById(R.id.riwayatList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        riwayatList = arrayListOf()
        getBarangData()
        return root
    }

    private fun getBarangData() {
        val uid = Firebase.auth.currentUser?.uid
        dbref = FirebaseDatabase.getInstance().getReference("dataRiwayat").orderByChild("idBuyer").equalTo(uid)

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    riwayatList.clear() // Clear the list before adding new data
                    for (barangSnapshot in snapshot.children) {
                        val riwayat = barangSnapshot.getValue(riwayat::class.java)
                        riwayatList.add(riwayat!!)
                    }
                    recyclerView.adapter = RiwayatAdapter(riwayatList)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                // Handle onCancelled event if needed
            }
        })
    }
}
