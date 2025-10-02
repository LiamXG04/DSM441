package com.example.jetpackcomposeapp.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import com.example.jetpackcomposeapp.model.Materia
@Composable
fun MateriaDetailScreen(materia: Materia) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = materia.nombre, style =
            MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Código: ${materia.codigo}")
        Text(text = "UVs: ${materia.uvs}")
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Descripción:")
        Text(text = materia.descripcion, style =
            MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Temas principales:")
        materia.temas.forEach { tema ->
            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                Icon(Icons.Default.Check, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = tema)
            }
        }
    }
}