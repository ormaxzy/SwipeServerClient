package com.example.server.presentation.log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.server.domain.model.LogEntry
import com.example.server.domain.usecase.GetLogsUseCase
import com.example.server.domain.usecase.DeleteAllLogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogViewModel @Inject constructor(
    private val getLogsUseCase: GetLogsUseCase,
    private val deleteAllLogsUseCase: DeleteAllLogsUseCase
) : ViewModel() {

    private val _logEntries = MutableStateFlow<List<LogEntry>>(emptyList())
    val logEntries: StateFlow<List<LogEntry>> get() = _logEntries

    init {
        loadLogs()
    }

    /**
     * Загружает логи с использованием GetLogsUseCase.
     */
    private fun loadLogs() {
        viewModelScope.launch {
            _logEntries.value = getLogsUseCase.execute()
        }
    }

    /**
     * Удаляет все логи.
     */
    fun deleteAllLogs() {
        viewModelScope.launch {
            deleteAllLogsUseCase.execute()
            _logEntries.value = emptyList() // Обновляем список логов после удаления
        }
    }
}
