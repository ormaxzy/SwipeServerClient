package com.example.server.data.network

import android.util.Log
import javax.inject.Inject
import com.example.common.model.GestureCommand
import io.ktor.websocket.DefaultWebSocketSession
import io.ktor.websocket.Frame
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Класс для управления клиентами WebSocket сервера.
 */
class ClientHandler @Inject constructor() {

    // Приватное состояние для хранения списка клиентов.
    private val _clients = MutableStateFlow<List<DefaultWebSocketSession>>(emptyList())

    /**
     * Добавление клиента в список.
     *
     * @param client Сессия WebSocket клиента.
     */
    fun addClient(client: DefaultWebSocketSession) {
        _clients.value += client
    }

    /**
     * Удаление клиента из списка.
     *
     * @param client Сессия WebSocket клиента.
     */
    fun removeClient(client: DefaultWebSocketSession) {
        _clients.value -= client
    }

    /**
     * Рассылка сообщения всем клиентам.
     *
     * @param message Команда жеста для рассылки.
     */
    suspend fun broadcast(message: GestureCommand) {
        val messageString = Json.encodeToString(message)
        _clients.value.forEach {
            try {
                it.send(Frame.Text(messageString))
            } catch (e: Exception) {
                Log.e("@@@ClientHandler", "Error broadcasting message: ${e.message}")
            }
        }
    }
}
