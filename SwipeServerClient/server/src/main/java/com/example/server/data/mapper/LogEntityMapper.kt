package com.example.server.data.mapper

import com.example.server.data.local.LogEntity
import com.example.server.domain.model.LogEntry

/**
 * Преобразует LogEntry в LogEntity.
 *
 * @return LogEntity, содержащий данные из LogEntry.
 */
fun LogEntry.toEntity(): LogEntity {
    return LogEntity(
        id = 0,  // ID будет автоматически генерироваться Room
        message = this.message,
        timestamp = this.timestamp
    )
}

/**
 * Преобразует LogEntity в LogEntry.
 *
 * @return LogEntry, содержащий данные из LogEntity.
 */
fun LogEntity.toDomainModel(): LogEntry {
    return LogEntry(
        message = this.message,
        timestamp = this.timestamp
    )
}
