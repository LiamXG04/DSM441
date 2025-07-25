package com.example.desafio


import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etMonto: EditText
    private lateinit var etPersonas: EditText
    private lateinit var etPersonalizado: EditText
    private lateinit var rgPropina: RadioGroup
    private lateinit var switchIVA: Switch
    private lateinit var tvResultado: TextView
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etMonto = findViewById(R.id.etMonto)
        etPersonas = findViewById(R.id.etPersonas)
        etPersonalizado = findViewById(R.id.etPersonalizado)
        rgPropina = findViewById(R.id.rgPropina)
        switchIVA = findViewById(R.id.switchIVA)
        tvResultado = findViewById(R.id.tvResultado)
        btnCalcular = findViewById(R.id.btnCalcular)
        btnLimpiar = findViewById(R.id.btnLimpiar)

        rgPropina.setOnCheckedChangeListener { _, checkedId ->
            etPersonalizado.visibility =
                if (checkedId == R.id.rbPersonalizada) View.VISIBLE else View.GONE
        }

        btnCalcular.setOnClickListener {
            calcular()
        }

        btnLimpiar.setOnClickListener {
            limpiar()
        }
    }

    private fun calcular() {
        val montoStr = etMonto.text.toString()
        val personasStr = etPersonas.text.toString()

        if (montoStr.isEmpty() || montoStr.toDoubleOrNull() == null || montoStr.toDouble() <= 0) {
            etMonto.error = "Ingrese un monto válido"
            return
        }

        if (personasStr.isEmpty() || personasStr.toIntOrNull() == null || personasStr.toInt() <= 0) {
            etPersonas.error = "Ingrese un número válido de personas"
            return
        }

        val monto = montoStr.toDouble()
        val personas = personasStr.toInt()
        var porcentaje = 0.0

        when (rgPropina.checkedRadioButtonId) {
            R.id.r10 -> porcentaje = 10.0
            R.id.r15 -> porcentaje = 15.0
            R.id.r20 -> porcentaje = 20.0
            R.id.rbPersonalizada -> {
                val personalizada = etPersonalizado.text.toString()
                if (personalizada.isEmpty() || personalizada.toDoubleOrNull() == null) {
                    etPersonalizado.error = "Ingrese un porcentaje válido"
                    return
                }
                porcentaje = personalizada.toDouble()
            }
            else -> {
                Toast.makeText(this, "Seleccione una opción de propina", Toast.LENGTH_SHORT).show()
                return
            }
        }

        val iva = if (switchIVA.isChecked) monto * 0.16 else 0.0
        val propina = monto * porcentaje / 100
        val total = monto + iva + propina
        val porPersona = total / personas

        val resultado = """
            Propina: $${String.format("%.2f", propina)}
            Total a pagar: $${String.format("%.2f", total)}
            Por persona: $${String.format("%.2f", porPersona)}
        """.trimIndent()

        tvResultado.text = resultado
    }

    private fun limpiar() {
        etMonto.setText("")
        etPersonas.setText("")
        etPersonalizado.setText("")
        etPersonalizado.visibility = View.GONE
        rgPropina.clearCheck()
        switchIVA.isChecked = false
        tvResultado.text = getString(R.string.resultado)
    }
}
