package com.example.server.presentation.log

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.server.R
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun LogScreen(
    onBackClick: () -> Unit,
    viewModel: LogViewModel = hiltViewModel()
) {
    val logEntries by viewModel.logEntries.collectAsState(initial = emptyList())

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = onBackClick) {
                    Text(text = stringResource(R.string.back))
                }
                Button(onClick = { viewModel.deleteAllLogs() }) {
                    Text(text = stringResource(R.string.delete_all_logs))
                }
            }
            LazyColumn(
                contentPadding = PaddingValues(16.dp)
            ) {
                items(logEntries) { logEntry ->
                    Text(text = "${formatTimestamp(logEntry.timestamp)}: ${logEntry.message}")
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

/**
 * Форматирует метку времени в удобочитаемый вид.
 *
 * @param timestamp Метка времени для форматирования.
 * @return Форматированная строка с меткой времени.
 */
fun formatTimestamp(timestamp: Long): String {
    val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return sdf.format(Date(timestamp))
}