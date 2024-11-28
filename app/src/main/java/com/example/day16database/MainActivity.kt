package com.example.day16database

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
     val signButton = findViewById<Button>(R.id.btnSignup)
        val etName = findViewById<TextInputEditText>(R.id.etName)
        val userId = findViewById<TextInputEditText>(R.id.etUserName)
        val userPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val etMail = findViewById<TextInputEditText>(R.id.etMail)

        signButton.setOnClickListener {

            val name = etName.text.toString()
            val mail = etMail.text.toString()
            val userId = userId.text.toString()
            val password = userPassword.text.toString()

            val user = User(name, mail, password, userId)
           database = FirebaseDatabase.getInstance().getReference("Users")

            database.child(userId).setValue(user).addOnSuccessListener {

                etName.text?.clear()
                Toast.makeText(this,"User Registered",Toast.LENGTH_SHORT).show()
            }.addOnSuccessListener {
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }

            val signIntext = findViewById<TextView>(R.id.tvSignIn)
            signIntext.setOnClickListener{
                val openSignInActivity = Intent(this, SignInActivity::class.java)
                startActivity(openSignInActivity)
            }
        }
    }
}