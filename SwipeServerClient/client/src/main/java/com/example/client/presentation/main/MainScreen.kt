package com.example.client.presentation.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.client.R

/**
 * Главный экран приложения.
 *
 * @param onConfigClick Callback для обработки нажатия на кнопку конфигурации.
 * @param onSettingsClick Callback для обработки нажатия на кнопку настроек.
 * @param onCheckPermissions Функция для проверки разрешений.
 * @param viewModel ViewModel для работы с состоянием подключения.
 */
@Composable
fun MainScreen(
    onConfigClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onCheckPermissions: () -> Boolean,
    viewModel: MainViewModel = hiltViewModel()
) {
    val isConnected by viewModel.isConnected.collectAsState(initial = false)
    val buttonText =
        if (isConnected) stringResource(id = R.string.pause) else stringResource(id = R.string.start)
    val ip = viewModel.getIp().orEmpty()
    val port = viewModel.getPort().orEmpty()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "IP: $ip, Port: $port", modifier = Modifier.padding(16.dp))
            Button(
                onClick = onConfigClick,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = stringResource(id = R.string.config))
            }
            Button(
                onClick = {
                    if (onCheckPermissions()) {
                        viewModel.toggleConnection()
                    }
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = buttonText)
            }
        }
        IconButton(
            onClick = onSettingsClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_settings_24),
                contentDescription = stringResource(id = R.string.settings)
            )
        }
    }
}

