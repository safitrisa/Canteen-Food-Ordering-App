package com.example.canteenfoodorderingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

class SplashScreen : AppCompatActivity() {

    // Deklarasi variabel
    private lateinit var topAnim: Animation
    private lateinit var bottomAnim: Animation
    private lateinit var splashImage: ImageView
    private lateinit var logoTextL1: TextView
    private lateinit var logoTextL2: TextView
    private val SPLASH_SCREEN: Long = 5000

    // Ketika file berjalan akan membuka SplashScreen.xml
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        // Mengisi variabel dengan animasi dan objek view
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)
        splashImage = findViewById(R.id.splash_image)
        logoTextL1 = findViewById(R.id.splash_Text1)
        logoTextL2 = findViewById(R.id.splash_Text2)

        // Menugaskan tiap objek dengan masing-masing animasi
        splashImage.animation = topAnim
        logoTextL1.animation = bottomAnim
        logoTextL2.animation = bottomAnim

        // Menjalankan fungsi startSplashScreen
        startSplashScreen()
    }

    // Setelah delay 5 detik berpindah ke halaman lain
    private fun startSplashScreen() {
        Handler().postDelayed({
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }, SPLASH_SCREEN)
    }
}
