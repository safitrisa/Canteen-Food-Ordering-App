package com.example.canteenfoodorderingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.canteenfoodorderingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Home())
        binding.navbarMenu.setOnItemSelectedListener {
            when(it){
                R.id.home -> replaceFragment(Home())
                R.id.history -> replaceFragment(History())
                R.id.status -> replaceFragment(StatusPembelian())
                R.id.profile -> replaceFragment(Profile())
                else -> {
                }
            }
            true
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransient = fragmentManager.beginTransaction()
        fragmentTransient.replace(R.id.framelayout,fragment)
        fragmentTransient.commit()
    }
}