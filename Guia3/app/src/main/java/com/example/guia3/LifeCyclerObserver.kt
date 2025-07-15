package com.example.guia3

import android.app.Activity
import android.graphics.Color
import android.widget.TextView
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class LifeCycleObserver(
    private val activity: Activity,
    private val txtAbierto: TextView,
    private val txtSuspendido: TextView,
    private val txtCerrado: TextView,
    private val txtEstado: TextView
) : DefaultLifecycleObserver {

    // Contadores para cada evento del ciclo de vida
    private var contadorAbierto = 0
    private var contadorSuspendido = 0
    private var contadorCerrado = 0

    /**
     * Se llama cuando la actividad entra en estado Started (visible para el usuario).
     */
    override fun onStart(owner: LifecycleOwner) {
        contadorAbierto++
        txtEstado.text = "Estado actual: onStart"
        activity.window.decorView.setBackgroundColor(Color.GREEN)
        actualizarPantalla()
    }

    /**
     * Se llama cuando la actividad entra en estado Paused (parcialmente visible, por ejemplo, al abrir otra app encima).
     */
    override fun onPause(owner: LifecycleOwner) {
        contadorSuspendido++
        txtEstado.text = "Estado actual: onPause"
        activity.window.decorView.setBackgroundColor(Color.YELLOW)
        actualizarPantalla()
    }

    /**
     * Se llama cuando la actividad es destruida (cerrada).
     */
    override fun onDestroy(owner: LifecycleOwner) {
        contadorCerrado++
        txtEstado.text = "Estado actual: onDestroy"
        activity.window.decorView.setBackgroundColor(Color.RED)
        actualizarPantalla()
    }

    /**
     * Actualiza los TextViews con los valores actuales de los contadores.
     */
    private fun actualizarPantalla() {
        txtAbierto.text = "Veces abierto: $contadorAbierto"
        txtSuspendido.text = "Veces suspendido: $contadorSuspendido"
        txtCerrado.text = "Veces cerrado: $contadorCerrado"
    }
}
