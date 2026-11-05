package com.example.part1assign

import android.content.Intent
import android.os.Bundle
import android.system.Os.write
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlin.jvm.java

class notes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_notes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lateinit var auth: FirebaseAuth
// ...
// Initialize Firebase Auth
        auth = Firebase.auth



        var txttittle: EditText = findViewById(R.id.txttittle)
        var txtwrite: EditText = findViewById(R.id.txtwrite)
        var btnsave: Button = findViewById(R.id.btnsave)
        var btndone: Button = findViewById(R.id.btndone)


        fun handleLogin() {
            val title = txttittle.text.toString().trim()
            val write = txtwrite.text.toString().trim()

// 3. Validation: Enforce security constraints (e.g., minimum password length)
            if (txttittle.length() < 20) {
                txttittle.error = "Do not exceed word count"
                txttittle.requestFocus()
                return
            }

            // 4. Authenticate & Navigate
            Toast.makeText(this, "Type anything, $txttittle!", Toast.LENGTH_SHORT).show()


            btnsave.setOnClickListener {
                // Replace 'NoteActivity::class.java' with the name of your journal screen activity
                val intent = Intent(this, Menu::class.java)
                startActivity(intent)
                finish() // Closes LoginActivity so clicking back won't return to the login screen
            }

            // 3. Validation: Enforce security constraints (e.g., minimum password length)
            if (txtwrite.length() < 200) {
                txtwrite.error = "Do not exceed word count"
                txtwrite.requestFocus()
                return
            }

            // 4. Authenticate & Navigate
            Toast.makeText(this, "Type anything, $txtwrite!", Toast.LENGTH_SHORT).show()


            btndone.setOnClickListener {
                // Replace 'NoteActivity::class.java' with the name of your journal screen activity
                val intent = Intent(this, Menu::class.java)
                startActivity(intent)
                finish() // Closes LoginActivity so clicking back won't return to the login screen
            }


            }

        }
    }

