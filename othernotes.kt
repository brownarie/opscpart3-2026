package com.example.part1assign

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore

class othernotes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_othernotes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

         val firestore = FirebaseFirestore.getInstance()

        val notesContainer = findViewById<LinearLayout>(R.id.notesContainer)
        val menuButton = findViewById<Button>(R.id.menuButton)

        // Journal notes
        val notes = listOf(
            "Remember to buy groceries.",
            "Ideas for my new project.",
            "Call Mom tonight.",
            "Read chapter 5.",
            "Meeting at 10:00 tomorrow."
        )

        // Display notes
        for (note in notes) {

            val noteText = TextView(this)

            noteText.text = note
            noteText.textSize = 16f
            noteText.setTextColor(Color.DKGRAY)
            noteText.setPadding(4, 12, 4, 12)

            notesContainer.addView(noteText)
        }

        // Three-dot menu
        menuButton.setOnClickListener {
            Toast.makeText(
                this,
                "Journal options",
                Toast.LENGTH_SHORT
            ).show()
        }


    }
}