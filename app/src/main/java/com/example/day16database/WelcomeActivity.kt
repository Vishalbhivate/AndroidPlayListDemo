package com.example.day16database

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)

        val mail = intent.getStringExtra(SignInActivity.KEY)
        val name = intent.getStringExtra(SignInActivity.KEY2)
        val userid = intent.getStringExtra(SignInActivity.KEY3)

        val welcomeText = findViewById<TextView>(R.id.tVwelcome)
        val mailText = findViewById<TextView>(R.id.tvMail)
        val idText = findViewById<TextView>(R.id.tvunique)
        welcomeText.text = "Welcome $name"
        mailText.text = "Mail : $mail"
        idText.text = "Userid : $userid"

    }
}