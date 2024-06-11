package com.example.server.domain.repository

import com.example.server.domain.model.LogEntry

interface LogRepository {

    /**
     * Сохранение лога в базе данных.
     *
     * @param logEntry Лог-запись, которую нужно сохранить.
     */
    suspend fun saveLog(logEntry: LogEntry)

    /**
     * Получение всех логов из базы данных.
     *
     * @return Список логов.
     */
    suspend fun getLogs(): List<LogEntry>

    /**
     * Удаление всех логов из базы данных.
     */
    suspend fun deleteAllLogs()
}