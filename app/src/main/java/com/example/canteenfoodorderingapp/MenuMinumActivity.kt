package com.example.canteenfoodorderingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MenuMinumActivity : AppCompatActivity() {
    private lateinit var dbref : DatabaseReference
    private lateinit var RecyclerView : RecyclerView
    private lateinit var ArrayList : ArrayList<ItemMakanMinum>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_minum)

        RecyclerView = findViewById(R.id.minumList)
        RecyclerView.layoutManager = LinearLayoutManager(this)
        RecyclerView.setHasFixedSize(true)

        ArrayList = arrayListOf<ItemMakanMinum>()
        getBarangData()
    }

    private fun getBarangData() {
        dbref = FirebaseDatabase.getInstance().getReference( "dataMinum")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (barangSnapshot in snapshot.children){

                        val barang = barangSnapshot.getValue(ItemMakanMinum::class.java)
                        ArrayList.add(barang!!)
                    }

                    RecyclerView.adapter = MakanMinumAdapter(ArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}
