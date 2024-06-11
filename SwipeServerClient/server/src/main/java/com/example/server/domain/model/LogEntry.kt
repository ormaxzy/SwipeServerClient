package com.example.server.domain.model

/**
 * Модель лог-записи.
 *
 * @param message Сообщение лога.
 * @param timestamp Временная метка лога.
 */
data class LogEntry(
    val message: String,
    val timestamp: Long
)
