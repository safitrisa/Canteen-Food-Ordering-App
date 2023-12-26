package com.example.canteenfoodorderingapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase

class Home: Fragment() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        firebaseAuth = FirebaseAuth.getInstance()
        val user = Firebase.auth.currentUser
        var uid: String? = null

        // Get the user's UID
        user?.let {
            uid = it.uid
        }

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val namaUser = root.findViewById<TextView>(R.id.textView2)

        // Retrieve and display user data from database
        if (uid != null) {
            val databaseReference = FirebaseDatabase.getInstance().getReference("dataUsers").child(
                uid!!
            )
            databaseReference.get().addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    val userData = snapshot.value as HashMap<String, Any>
                    val nama = userData["nama"].toString()
                    namaUser.text = "Halo $nama !"
                } else {
                    namaUser.text = "Name : Not found"
                }
            }.addOnFailureListener {
                Log.e("ProfileFragment", "Error retrieving user data", it)
            }
        } else {
            Log.e("ProfileFragment", "No user logged in")
        }

        return root
    }

    // pindah ke halaman makan / minum :
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menu_makan = view.findViewById<ConstraintLayout>(R.id.menu_makan)
        menu_makan.setOnClickListener {
            val intent = Intent(requireContext(), MenuMakanActivity::class.java)
            startActivity(intent)
        }
        val menu_minum = view.findViewById<ConstraintLayout>(R.id.menu_minum)
        menu_minum.setOnClickListener {
            val intent = Intent(requireContext(), MenuMinumActivity::class.java)
            startActivity(intent)
        }
    }
}
