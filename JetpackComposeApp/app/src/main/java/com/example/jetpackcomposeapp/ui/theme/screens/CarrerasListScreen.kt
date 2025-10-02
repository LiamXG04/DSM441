package com.example.jetpackcomposeapp.ui.theme.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeapp.model.Materia
@Composable
fun CarrerasListScreen(
    carreras: Map<String, List<Materia>>,
    onCarreraClick: (String) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        items(carreras.keys.toList()) { carreraName ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable { onCarreraClick(carreraName) }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = carreraName, style =
                        MaterialTheme.typography.titleLarge)
                    Text(
                        text = "${carreras[carreraName]?.size ?: 0} materias",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}