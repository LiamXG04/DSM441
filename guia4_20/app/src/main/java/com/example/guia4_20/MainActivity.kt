package com.example.guia4_20

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
class MainActivity : AppCompatActivity() {
    private lateinit var editPhone: EditText
    private lateinit var btnLlamar: Button
    private val REQUEST_CALL = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editPhone = findViewById(R.id.editPhone)
        btnLlamar = findViewById(R.id.btnLlamar)
        btnLlamar.setOnClickListener {
            val numero = editPhone.text.toString()
            if (numero.isNotEmpty()) {
                hacerLlamada(numero)
            } else {
                Toast.makeText(this, "Ingresá un número", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun hacerLlamada(numero: String) {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$numero")
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) !=
            PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), REQUEST_CALL)
        } else {
            startActivity(intent)
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val numero = editPhone.text.toString()
                hacerLlamada(numero)
            } else {
                Toast.makeText(this, "Permiso de llamada denegado", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
