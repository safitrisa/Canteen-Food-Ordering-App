package com.example.canteenfoodorderingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.canteenfoodorderingapp.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.textView.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()
            val confirmPass = binding.confirmPassEt.text.toString()
            val nama = binding.namaET.text.toString()
            val noTelp = binding.noTelpET.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Registration successful, save additional information to the database
                                val user = firebaseAuth.currentUser
                                user?.let {
                                    val userId = it.uid
                                    val databaseReference =
                                        FirebaseDatabase.getInstance().getReference("dataUsers").child(userId)

                                    val userData = HashMap<String, Any>()
                                    userData["email"] = email
                                    userData["nama"] = nama
                                    userData["noTelp"] = noTelp

                                    databaseReference.setValue(userData)
                                        .addOnCompleteListener { databaseTask ->
                                            if (databaseTask.isSuccessful) {
                                                val intent = Intent(this, SignInActivity::class.java)
                                                startActivity(intent)
                                                finish() // Finish the SignUpActivity to prevent going back
                                            } else {
                                                Toast.makeText(
                                                    this,
                                                    "Failed to save user data to database",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }
                                }
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(
                                    this,
                                    "Authentication failed: ${task.exception?.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                } else {
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()
            }
        }

    }
    // Jika pengguna sudah SignIn maka akan langsung ke MainActivity
    override fun onStart() {
        super.onStart()
        if(firebaseAuth.currentUser != null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
