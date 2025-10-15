package com.example.jetpackcomposeapp.navigation

import android.net.Uri
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.jetpackcomposeapp.ui.screens.CarreraDetailScreen
import com.example.jetpackcomposeapp.viewmodel.CarrerasViewModel
import com.example.jetpackcomposeapp.ui.screens.CarrerasListScreen
import com.example.jetpackcomposeapp.ui.screens.MateriaDetailScreen
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Carrera : Screen("carrera/{carreraName}") {
        fun createRoute(carreraName: String) =
            "carrera/${Uri.encode(carreraName)}"
    }
    object Materia : Screen("materia/{codigo}") {
        fun createRoute(codigo: String) = "materia/$codigo"
    }
}
@Composable
fun AppNavHost(navController: NavHostController) {
    val viewModel: CarrerasViewModel = viewModel()
    NavHost(navController = navController, startDestination =
        Screen.Home.route) {
        composable(Screen.Home.route) {
            CarrerasListScreen(
                carreras = viewModel.carreras.value,
                onCarreraClick = { name ->
                    navController.navigate(Screen.Carrera.createRoute(name))
                }
            )
        }
        composable(
            route = Screen.Carrera.route,
            arguments = listOf(navArgument("carreraName") { type =
                NavType.StringType })
        ) { backStackEntry ->
            val carreraName =
                Uri.decode(backStackEntry.arguments?.getString("carreraName") ?: "")
            CarreraDetailScreen(
                carreraName = carreraName,
                materias = viewModel.carreras.value[carreraName] ?:
                emptyList(),
                onMateriaClick = { codigo ->
                    navController.navigate(Screen.Materia.createRoute(codigo))
                }
            )
        }
        composable(
            route = Screen.Materia.route,
            arguments = listOf(navArgument("codigo") { type =
                NavType.StringType })
        ) { backStackEntry ->
            val codigo = backStackEntry.arguments?.getString("codigo") ?: ""
            val materia = viewModel.buscarMateriaPorCodigo(codigo)
            if (materia != null) {
                MateriaDetailScreen(materia = materia)
            } else {
                Text(text = "Materia no encontrada")
            }
        }
    }
}
