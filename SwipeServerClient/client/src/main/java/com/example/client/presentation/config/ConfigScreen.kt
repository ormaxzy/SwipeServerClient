package com.example.client.presentation.config

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import com.example.client.R

@Composable
fun ConfigScreen(
    onSaveClick: () -> Unit,
    viewModel: ConfigViewModel = hiltViewModel()
) {
    var ip by remember { mutableStateOf(TextFieldValue("")) }
    var port by remember { mutableStateOf(TextFieldValue("")) }

    // Инициализация значений IP и порта при запуске экрана.
    LaunchedEffect(Unit) {
        ip = TextFieldValue(viewModel.getIp() ?: "")
        port = TextFieldValue(viewModel.getPort() ?: "")
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = stringResource(id = R.string.ip_address), fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = ip,
                onValueChange = { ip = it },
                label = { Text(stringResource(id = R.string.enter_ip)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(id = R.string.port), fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = port,
                onValueChange = { port = it },
                label = { Text(stringResource(id = R.string.enter_port)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    viewModel.saveConfig(ip.text, port.text)
                    onSaveClick()
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = stringResource(id = R.string.save))
            }
        }
    }
}
