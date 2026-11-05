package com.example.part1assign

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lateinit var auth: FirebaseAuth
// ...
// Initialize Firebase Auth
        auth = Firebase.auth

        val Txnewnote = findViewById<LinearLayout>(R.id.Txnewnote)
        val TextSet = findViewById<LinearLayout>(R.id.TextSet)
        val Textnotes = findViewById<LinearLayout>(R.id.Textnotes)




        Txnewnote.setOnClickListener {

            val intent = Intent(this, notes::class.java)

            startActivity(intent)
            }


        TextSet.setOnClickListener {

            val intent = Intent(this, Settings::class.java)

            startActivity(intent)
        }


        Textnotes.setOnClickListener {

            val intent = Intent(this, othernotes::class.java)

            startActivity(intent)
        }


        val btnlogout = findViewById<Button>(R.id.btnlogout)

        btnlogout.setOnClickListener {

            val intent = Intent(this, Login::class.java)

            startActivity(intent)
        }
    }
}