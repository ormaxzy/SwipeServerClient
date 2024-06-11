package com.example.server.domain.usecase

import com.example.server.domain.model.LogEntry
import com.example.server.domain.repository.LogRepository

class GetLogsUseCase(
    private val logRepository: LogRepository
) {

    /**
     * Получает все логи.
     *
     * @return Список всех логов.
     */
    suspend fun execute(): List<LogEntry> {
        return logRepository.getLogs()
    }
}
