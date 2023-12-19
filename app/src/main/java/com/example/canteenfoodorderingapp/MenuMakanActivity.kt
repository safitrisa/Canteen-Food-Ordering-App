package com.example.canteenfoodorderingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MenuMakanActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: MakanMinumAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_makan)
        init()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
    private fun init(){
        recyclerView = findViewById(R.id.barangList)
        var data = ArrayList<ItemMakanMinum>()
        data.add(ItemMakanMinum("Toko A","Nasi Goreng", "15.000" , "20"))
        adapter = MakanMinumAdapter(data)
    }
}
