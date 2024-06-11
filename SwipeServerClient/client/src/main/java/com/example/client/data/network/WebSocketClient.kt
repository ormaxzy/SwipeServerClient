package com.example.client.data.network

import android.util.Log
import com.example.client.service.GestureManager
import com.example.common.model.GestureCommand
import com.example.common.model.GestureReport
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.ws
import io.ktor.websocket.DefaultWebSocketSession
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class WebSocketClient(private val gestureManager: GestureManager) {

    private val client = HttpClient {
        install(WebSockets)
    }

    private val _isConnected = MutableStateFlow(false)
    val isConnected = _isConnected.asStateFlow()

    private lateinit var session: DefaultWebSocketSession

    /**
     * Подключение к WebSocket серверу по указанным IP и порту.
     */
    fun connect(ip: String, port: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                client.ws(host = ip, port = port.toInt(), path = "/") {
                    session = this
                    _isConnected.value = true
                    try {
                        for (frame in incoming) {
                            frame as? Frame.Text ?: continue
                            val receivedText = frame.readText()
                            val command = Json.decodeFromString<GestureCommand>(receivedText)
                            val report = gestureManager.performGesture(command)
                            sendGestureReport(report)
                        }
                    } catch (e: ClosedReceiveChannelException) {
                        e.printStackTrace()
                    } finally {
                        _isConnected.value = false
                    }
                }
            } catch (e: Exception) {
                Log.e("WebSocketClient","Error connecting to server: ${e.message}")
                _isConnected.value = false
            }
        }
    }

    /**
     * Отключение от WebSocket сервера.
     */
    fun disconnect() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                client.close()
                _isConnected.value = false
            } catch (e: Exception) {
                Log.e("WebSocketClient","Error disconnecting: ${e.message}")
            }
        }
    }

    /**
     * Отправка отчета о жесте на сервер.
     */
    private fun sendGestureReport(report: GestureReport) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                session.send(Frame.Text(Json.encodeToString(report)))
            } catch (e: Exception) {
                Log.e("WebSocketClient","Error sending report: ${e.message}")
            }
        }
    }
}
