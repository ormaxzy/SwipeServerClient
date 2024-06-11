package com.example.server.presentation.nav


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.server.presentation.config.ConfigScreen
import com.example.server.presentation.log.LogScreen
import com.example.server.presentation.main.MainScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    // Константы для навигационных путей
    val mainRoute = "main"
    val configRoute = "config"
    val logRoute = "log"

    NavHost(
        navController = navController,
        startDestination = mainRoute
    ) {
        composable(mainRoute) {
            MainScreen(
                onConfigClick = { navController.navigate(configRoute) },
                onLogClick = { navController.navigate(logRoute) }
            )
        }

        composable(configRoute) {
            ConfigScreen(
                onSaveClick = { navController.popBackStack() }
            )
        }

        composable(logRoute) {
            LogScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
