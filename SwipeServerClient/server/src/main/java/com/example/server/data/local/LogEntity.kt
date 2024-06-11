package com.example.server.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Сущность логов для базы данных.
 *
 * @param id Уникальный идентификатор лога.
 * @param message Сообщение лога.
 * @param timestamp Временная метка лога.
 */
@Entity(tableName = "logs")
data class LogEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val message: String,
    val timestamp: Long
)