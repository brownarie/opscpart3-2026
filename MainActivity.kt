package com.example.part1assign

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.part1assign.ui.theme.Part1AssignTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.actionCodeSettings
import com.google.firebase.auth.auth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Part1AssignTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Part1AssignTheme {
        Greeting("Android")
    }
}

@Composable
fun SubscribeScreen() {
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA))
            .padding(24.dp)
    ) {
        // Main Title
        Text(
            text = "Subscribe",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF8E24AA) // Purple
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Label Above Input
        Text(
            text = "Email",
            fontSize = 20.sp,
            color = Color(0xFF3F51B5) // Navy/Blue
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Email TextField with light grey background
        TextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("Email", color = Color.Gray) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF2F2F2),
                unfocusedContainerColor = Color(0xFFF2F2F2),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(4.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Pink Subscribe Button
        Button(
            onClick = { /* Handle subscription logic */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF8E1F4), // Light pink
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "SUBSCRIBE",
                fontWeight = FontWeight.Bold
            )
        }
    }


    
    
    val actionCodeSettings = actionCodeSettings {
        // URL you want to redirect back to. The domain (www.example.com) for this
        // URL must be whitelisted in the Firebase Console.
        url = "https://www.example.com/finishSignUp?cartId=1234"
        // This must be true
        handleCodeInApp = true
        setIOSBundleId("com.example.ios")
        setAndroidPackageName(
            "com.example.android",
            true, // installIfNotAvailable
            "12", // minimumVersion
        )
    }

    Firebase.auth.sendSignInLinkToEmail(email, actionCodeSettings)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val TAG = null
                Log.d(TAG, "Email sent.")
            }
        }
// Construct the email link credential from the current URL.
    val emailLink = ""
    val credential = EmailAuthProvider.getCredentialWithLink(email, emailLink)

// Re-authenticate the user with this credential.
    Firebase.auth.currentUser!!.reauthenticateAndRetrieveData(credential)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // User is now successfully reauthenticated
            } else {
                Log.e(TAG, "Error reauthenticating", task.exception)
            }
        }
    Firebase.auth.signOut()
}


