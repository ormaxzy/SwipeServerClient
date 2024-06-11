package com.example.client.presentation.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.client.presentation.config.ConfigScreen
import com.example.client.presentation.main.MainScreen

/**
 * Граф навигации для приложения.
 *
 * @param navController Контроллер навигации.
 * @param onSettingsClick Callback для обработки нажатия на кнопку настроек.
 * @param onCheckPermissions Функция для проверки разрешений.
 */
@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    onSettingsClick: () -> Unit,
    onCheckPermissions: () -> Boolean
) {
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainScreen(
                onConfigClick = { navController.navigate("config") },
                onSettingsClick = onSettingsClick,
                onCheckPermissions = onCheckPermissions
            )
        }
        composable("config") {
            ConfigScreen(
                onSaveClick = { navController.popBackStack() }
            )
        }
    }
}
