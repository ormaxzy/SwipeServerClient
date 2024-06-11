package com.example.common.model

import kotlinx.serialization.Serializable

/**
 * Данные команды для выполнения жеста.
 *
 * @param type Тип команды.
 * @param duration Продолжительность жеста.
 * @param text Текст для ввода (если применимо).
 * @param mX Начальная координата X для жеста.
 * @param mY Начальная координата Y для жеста.
 * @param lX Конечная координата X для жеста.
 * @param lY Конечная координата Y для жеста.
 */
@Serializable
data class GestureCommand(
    val type: String,
    val duration: Long,
    val text: String? = null,
    val mX: Float,
    val mY: Float,
    val lX: Float,
    val lY: Float
)
