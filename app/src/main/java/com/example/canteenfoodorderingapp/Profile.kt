package com.example.canteenfoodorderingapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

// belum ditambahkan no telp
class Profile : Fragment() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        firebaseAuth = FirebaseAuth.getInstance()
        val user = Firebase.auth.currentUser
        var uid: String? = null
        var name: String? = null
        var email: String? = null

        // Get the user's UID
        user?.let {
            uid = it.uid
            name = it.displayName // nama belum ditambahkan, perlu menambah form input SignIn
            email = it.email
        }

        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        val namaUser = root.findViewById<TextView>(R.id.nama_user)
        val emailUser = root.findViewById<TextView>(R.id.email_user)
        val signOut: Button = root.findViewById(R.id.signout)
        signOut.setOnClickListener {
            firebaseAuth.signOut()
        }

        // Retrieve and display user data from database
        if (uid != null) {
            val databaseReference = FirebaseDatabase.getInstance().getReference("dataUsers").child(
                uid!!
            )
            databaseReference.get().addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    val userData = snapshot.value as HashMap<String, Any>
                    val nama = userData["nama"].toString()
                    namaUser.text = "Name : $nama"
                } else {
                    namaUser.text = "Name : Not found"
                }
            }.addOnFailureListener {
                Log.e("ProfileFragment", "Error retrieving user data", it)
            }
        } else {
            Log.e("ProfileFragment", "No user logged in")
        }

        // Set the email of the user
        emailUser.text = "Email : $email"
        return root
    }
}

