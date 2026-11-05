package com.example.part1assign

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
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

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }


        lateinit var auth: FirebaseAuth
// ...
// Initialize Firebase Auth
        auth = Firebase.auth

        var Euser: EditText = findViewById(R.id.Euser)
        var Tpass: EditText = findViewById(R.id.Tpass)
        var btnlog: Button = findViewById(R.id.btnlog)


        btnlog.setOnClickListener {
            val user = Euser.text.toString().trim()
            val pass = Tpass.text.toString().trim()

            if (user.isEmpty()) {
                Euser.error = "Username required"
                return@setOnClickListener
            }

            if (pass.isEmpty()) {
                Tpass.error = "Password required"
                return@setOnClickListener
            }

            // Perform login logic (e.g., Firebase Auth or API Call)
            Toast.makeText(this, "Logging in $user...", Toast.LENGTH_SHORT).show()
        }

        // Set action for LOGIN button

        fun handleLogin() {
            val username = Euser.text.toString().trim()
            val password = Tpass.text.toString().trim()


            // 3. Validation: Enforce security constraints (e.g., minimum password length)
            if (password.length < 6) {
                Tpass.error = "Password must be at least 6 characters long"
                Tpass.requestFocus()
                return
            }

            // 4. Authenticate & Navigate
            Toast.makeText(this, "Welcome back, $username!", Toast.LENGTH_SHORT).show()

            // Replace 'NoteActivity::class.java' with the name of your journal screen activity
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
            finish() // Closes LoginActivity so clicking back won't return to the login screen
        }





    }
}