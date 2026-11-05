package com.example.part1assign

import android.content.Intent
import android.os.Bundle
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

class backAPI : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_back_api)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

       lateinit var auth: FirebaseAuth
// ...
// Initialize Firebase Auth
        auth = Firebase.auth


        var Edmail: EditText = findViewById(R.id.Edmail)
        var btnsub: Button = findViewById(R.id.btnsub)


        fun handleLogin() {
            val email = Edmail.text.toString().trim()
            val subscri = btnsub.text.toString().trim()


            // 3. Validation: Enforce security constraints (e.g., minimum password length)
            if (subscri.length < 15) {
                btnsub.error = "Password must be at least 15 characters long"
                btnsub.requestFocus()
                return
            }

            // 4. Authenticate & Navigate
            Toast.makeText(this, "Welcome back, $email!", Toast.LENGTH_SHORT).show()

            // Replace 'NoteActivity::class.java' with the name of your journal screen activity
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
            finish() // Closes LoginActivity so clicking back won't return to the login screen
        }


    }
}