package com.example.part1assign

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlin.jvm.java
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.concurrent.thread


class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lateinit var auth: FirebaseAuth
// ...
// Initialize Firebase Auth
        auth = Firebase.auth

        lateinit var Eduser: EditText
        lateinit var EdEmail: EditText
        lateinit var Txtpass: EditText
        lateinit var btnsign: Button



    }

    var Eduser: EditText = findViewById(R.id.Eduser)
    var EdEmail: EditText = findViewById(R.id.EdEmail)
    var Txtpass: EditText = findViewById(R.id.Txtpass)
    var btnsign: Button = findViewById(R.id.btnsign)


    // Set action for LOGIN button

    private fun handleLogin() {
        val username = Eduser.text.toString().trim()
        val email = EdEmail.text.toString().trim()
        val password = Txtpass.text.toString().trim()

        // 1. Validation: Ensure no fields are empty
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // 2. Validation: Check for a structurally valid email address
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            EdEmail.error = "Please enter a valid email address"
            EdEmail.requestFocus()
            return
        }

        // 3. Validation: Enforce security constraints (e.g., minimum password length)
        if (password.length < 6) {
            Txtpass.error = "Password must be at least 6 characters long"
            Txtpass.requestFocus()
            return
        }




        thread {
            val rowData = mapOf(
                "username" to username,
                "email" to email,
                "password" to password,
            )

            val response = insertRow(
                tableName = "user_2cb2745c_users",
                data = rowData
            )

            // Update UI on main thread
            runOnUiThread {


                println(response)
                // or show Toast / TextView
                Toast.makeText(this,response.toString(),Toast.LENGTH_LONG).show()


                // 4. Authenticate & Navigate
                Toast.makeText(this, "Welcome back, $username!", Toast.LENGTH_SHORT).show()
                // Replace 'NoteActivity::class.java' with the name of your journal screen activity
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
                finish() // Closes LoginActivity so clicking back won't return to the login screen



            }
        }










    }

    fun signUp(view: View) {

        handleLogin()



    }



    fun insertRow(
        tableName: String,
        data: Map< String, Any?>
    ): String? {
        val url = URL("https://part2a-31bae.firebaseapp.com/__/auth/action?mode=action&oobCode=code")
        val connection = url.openConnection() as HttpURLConnection

        return try {
            connection.requestMethod = "POST"
            connection.setRequestProperty("Content-Type", "application/json")
            connection.setRequestProperty("Accept", "application/json")
            connection.doOutput = true

            // Build JSON payload (include nulls as empty strings if needed)
            val payload = JSONObject()
            for ((key, value) in data) {
                if (key != "id") {
                    payload.put(key, value ?: "")
                }
            }

            // Send JSON payload
            connection.outputStream.use { os ->
                os.write(payload.toString().toByteArray(Charsets.UTF_8))
                os.flush()
            }

            // Read response
            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            val response = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                response.append(line)
            }
            reader.close()
            response.toString()

        } catch (e: Exception) {
            e.printStackTrace()
            null
        } finally {
            connection.disconnect()
        }
    }


}


