package com.example.guia8

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
class AddPersonaActivity : AppCompatActivity() {
    private val dbRef =
        FirebaseDatabase.getInstance().reference.child("personas")
    private var editId: String? = null
    private lateinit var etNombre: EditText
    private lateinit var etDui: EditText
    private lateinit var btnGuardar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_persona)
        etNombre = findViewById(R.id.etNombre)
        etDui = findViewById(R.id.etDui)
        btnGuardar = findViewById(R.id.btnGuardar)
        editId = intent.getStringExtra(EXTRA_ID)
        etNombre.setText(intent.getStringExtra(EXTRA_NOMBRE))
        etDui.setText(intent.getStringExtra(EXTRA_DUI))
        btnGuardar.setOnClickListener { guardar() }
    }
    private fun guardar() {
        val nombre = etNombre.text?.toString()?.trim().orEmpty()
        val dui = etDui.text?.toString()?.trim().orEmpty()
        if (nombre.isEmpty()) {
            etNombre.error = "Requerido"
            return
        }
        if (!Regex("^[0-9]{8}-[0-9]$").matches(dui)) {
            etDui.error = "Formato inválido (ej: 01234567-8)"
            return
        }
        (editId == null) {
            val id = dbRef.push().key ?: return
            val persona = Persona(id, dui, nombre)
            dbRef.child(id).setValue(persona)
                .addOnSuccessListener { finish() }
                .addOnFailureListener { Toast.makeText(this, "Error",
                    Toast.LENGTH_SHORT).show() }
        } else {
            val persona = Persona(editId, dui, nombre)
            dbRef.child(editId!!).setValue(persona)
                .addOnSuccessListener { finish() }
                .addOnFailureListener { Toast.makeText(this, "Error",
                    Toast.LENGTH_SHORT).show() }
        }
    }
    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_NOMBRE = "extra_nombre"
        const val EXTRA_DUI = "extra_dui"
    }
}