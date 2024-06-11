package com.example.server.domain.usecase

import com.example.server.domain.model.LogEntry
import com.example.server.domain.repository.LogRepository

class SaveLogUseCase(
    private val logRepository: LogRepository
) {

    /**
     * Сохраняет лог.
     *
     * @param logEntry Запись лога для сохранения.
     */
    suspend fun execute(logEntry: LogEntry) {
        logRepository.saveLog(logEntry)
    }
}
