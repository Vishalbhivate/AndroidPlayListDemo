package com.example.day16database

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlin.coroutines.CoroutineContext

class SignInActivity : AppCompatActivity() {

   private lateinit var databaseReference: DatabaseReference
   companion object {
       const val KEY = "com.example.day16database.SigninActivity.mail"
       const val KEY2 = "com.example.day16database.SigninActivity.name"
       const val KEY3 = "com.example.day16database.SigninActivity.id"
   }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)

        val signInButton = findViewById<Button>(R.id.btnSignIn)
        val userName = findViewById<TextInputEditText>(R.id.etUserName)

        signInButton.setOnClickListener{

           val uniqueId = userName.text.toString()
            if(uniqueId.isNotEmpty()){
                readData(uniqueId)
            }else{
                Toast.makeText(this,"Please enter user name",Toast.LENGTH_SHORT).show()
            }

        }

    }//onCreat Method Over

    private fun readData(uniqueId: String) {
      databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        databaseReference.child(uniqueId).get().addOnSuccessListener {

          // if user exist or not
            if (it.exists()) {
                // welcome user in your app, with intent and also pass
                val email = it.child("email").value
                val name = it.child("name").value
                val userId = it.child("uniqueId").value

            val intentwelcome = Intent(this, WelcomeActivity::class.java)
                intentwelcome.putExtra(KEY,email.toString())
                intentwelcome.putExtra(KEY2,name.toString())
                intentwelcome.putExtra(KEY3,userId.toString())
            startActivity(intentwelcome)
            }else{
                Toast.makeText(this,"User doss not exist",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this,"Failed, Enter in DB",Toast.LENGTH_SHORT).show()
        }
    }
}