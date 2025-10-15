package com.example.guia07


import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.example.guia07.LoginActivity
import com.example.guia07.MainActivity
import android.widget.EditText


private fun signUpUser() {
    val context = this as AppCompatActivity // Cast para poder usar 'this' en Toast y Intent

    val email = etEmail.text.toString()
    val pass = etPass.text.toString()
    val confirmPassword = etConfPass.text.toString()

    if (email.isBlank() || pass.isBlank() || confirmPassword.isBlank()) {
        Toast.makeText(context, "Email and Password can't be blank",
            Toast.LENGTH_SHORT).show()
        return
    }

    if (pass != confirmPassword) {
        Toast.makeText(context, "Password and Confirm Password do not match",
            Toast.LENGTH_SHORT).show()
        return
    }

    auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(context) { task ->
        if (task.isSuccessful) {
            Toast.makeText(context, "Successfully Registered!",
                Toast.LENGTH_SHORT).show()
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
            context.finish() // Opcional: cierra la actividad de registro
        } else {
            val errorMessage = task.exception?.message ?: "Sign Up Failed!"
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
}



private fun login() {
    val context = this as AppCompatActivity // Cast para poder usar 'this' en Toast y Intent

    val email = etEmail.text.toString()
    val pass = etPass.text.toString()

    if (email.isBlank() || pass.isBlank()) {
        Toast.makeText(context, "Email and Password can't be blank",
            Toast.LENGTH_SHORT).show()
        return
    }

    auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(context) { task ->
        if (task.isSuccessful) {
            Toast.makeText(context, "Successfully Logged In",
                Toast.LENGTH_SHORT).show()
            // Redirigir a la actividad principal
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
            context.finish() // Opcional: cierra la actividad de login
        } else {
            // Muestra un mensaje de error si falla el login
            val errorMessage = task.exception?.message ?: "Log In failed"
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
}