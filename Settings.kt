package com.example.part1assign

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        var btnapi: Button = findViewById(R.id.btnapi)
        var btnexit: Button = findViewById(R.id.btnexit)



        // Redirects directly to the API web console URL
        btnapi.setOnClickListener {
            val apiKeyUrl = "https://google.com"
            val intent = Intent(Intent.ACTION_VIEW,  Uri.parse(apiKeyUrl))
            startActivity(intent)
        }

        btnapi.setOnClickListener {
            val intent = Intent(this, backAPI::class.java)
            finishAffinity()
        }
        // Entirely closes the application stack
        btnexit.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            finishAffinity()
        }




    }
}