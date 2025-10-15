package com.example.guia07

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

lateinit var etEmail: EditText
lateinit var etConfPass: EditText
private lateinit var etPass: EditText
private lateinit var btnSignUp: Button
lateinit var tvRedirectLogin: TextView

private lateinit var auth: FirebaseAuth
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_register)
    etEmail = findViewById(R.id.etSEmailAddress)
    etConfPass = findViewById(R.id.etSConfPassword)
    etPass = findViewById(R.id.etSPassword)
    btnSignUp = findViewById(R.id.btnSSigned)
    tvRedirectLogin = findViewById(R.id.tvRedirectLogin)

    auth = Firebase.auth
    btnSignUp.setOnClickListener {
        signUpUser()
    }
    tvRedirectLogin.setOnClickListener {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}