package com.example.server.presentation.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.server.R

@Composable
fun MainScreen(
    onConfigClick: () -> Unit,
    onLogClick: () -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val isConnected by viewModel.isConnected.collectAsState(initial = false)
    val port = viewModel.getPort().orEmpty()
    val ipAddress by viewModel.ipAddress.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "IP Address: $ipAddress", modifier = Modifier.padding(16.dp))
            Text(text = "Port: $port", modifier = Modifier.padding(16.dp))
            Button(
                onClick = onConfigClick,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = stringResource(R.string.config))
            }
            Button(
                onClick = onLogClick,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = stringResource(R.string.logs))
            }
            Button(
                onClick = { viewModel.toggleServer() },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = if (isConnected) stringResource(R.string.stop) else stringResource(R.string.start))
            }
        }
    }
}


