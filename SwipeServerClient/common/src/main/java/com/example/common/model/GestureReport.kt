package com.example.common.model

import kotlinx.serialization.Serializable

/**
 * Отчет о выполнении жеста.
 *
 * @param gestureType Тип жеста.
 * @param success Флаг успешного выполнения жеста.
 * @param timestamp Временная метка выполнения жеста.
 */
@Serializable
data class GestureReport(
    val gestureType: String,
    val success: Boolean,
    val timestamp: Long
)
