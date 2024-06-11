package com.example.server.domain.usecase

import com.example.server.domain.repository.LogRepository
import javax.inject.Inject

class DeleteAllLogsUseCase @Inject constructor(
    private val logRepository: LogRepository
) {

    /**
     * Удаляет все логи.
     */
    suspend fun execute() {
        logRepository.deleteAllLogs()
    }
}
