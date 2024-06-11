package com.example.server.data.network

import android.util.Log
import com.example.common.model.GestureCommand
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.routing.routing
import io.ktor.server.websocket.WebSockets
import io.ktor.server.websocket.pingPeriod
import io.ktor.server.websocket.webSocket
import io.ktor.websocket.DefaultWebSocketSession
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.time.Duration
import javax.inject.Inject
import com.example.common.model.GestureReport
import com.example.server.domain.model.LogEntry
import com.example.server.domain.usecase.SaveLogUseCase
import io.ktor.server.engine.ApplicationEngine
import io.ktor.websocket.CloseReason
import io.ktor.websocket.close

/**
 * WebSocket сервер для обработки подключений и выполнения жестов.
 */
class WebSocketServer @Inject constructor(
    private val clientHandler: ClientHandler,
    private val gesturesList: GesturesList,
    private val saveLogUseCase: SaveLogUseCase
) {

    private var server: ApplicationEngine? = null

    /**
     * Запуск сервера на указанном порту.
     *
     * @param port Порт для запуска сервера.
     */
    fun start(port: Int) {
        server = embeddedServer(Netty, port = port) {
            install(WebSockets) {
                pingPeriod = Duration.ofMinutes(1)
            }
            routing {
                webSocket("/") {
                    try {
                        clientHandler.addClient(this)
                        launch {
                            sendGestureCommands(this@webSocket)
                        }
                        for (frame in incoming) {
                            frame as? Frame.Text ?: continue
                            val receivedText = frame.readText()
                            if (receivedText.contains("type")) {
                                val command = Json.decodeFromString<GestureCommand>(receivedText)
                                clientHandler.broadcast(command)
                            } else {
                                val report = Json.decodeFromString<GestureReport>(receivedText)
                                saveLog(report)
                            }
                        }
                    } catch (e: ClosedReceiveChannelException) {
                        Log.e("@@@WebSocketServer", "Client disconnected with error: ${e.message}")
                    } finally {
                        clientHandler.removeClient(this)
                    }
                }
            }
        }
        server?.start(wait = true)
    }

    /**
     * Остановка сервера.
     */
    fun stop() {
        server?.stop(1000, 1000)
    }

    /**
     * Отправка команд жестов клиентам.
     *
     * @param session Сессия WebSocket клиента.
     */
    private fun sendGestureCommands(session: DefaultWebSocketSession) {
        CoroutineScope(Dispatchers.IO).launch {
            val gestureCommands = listOf(
                gesturesList.createSwipeDownCommand(),
                gesturesList.createSwipeUpCommand(),
                gesturesList.createTapSearchBarCommand(),
                gesturesList.createTypeSearchQueryCommand("github.com/ormaxzy"),
                gesturesList.createStartSearchCommand(),
                gesturesList.createTapSearchBarCommand(),
                gesturesList.createTypeSearchQueryCommand("nti.team"),
                gesturesList.createStartSearchCommand(),
                gesturesList.createTapThreeDotsMenuCommand(),
                gesturesList.createAddToFavoritesCommand()
            )

            for (command in gestureCommands) {
                try {
                    session.send(Frame.Text(Json.encodeToString(command)))
                    delay(2000) // Delay between commands
                } catch (e: Exception) {
                    Log.e("@@@WebSocketServer", "Error sending command: ${e.message}")
                    break
                }
            }

            try {
                session.close(CloseReason(CloseReason.Codes.NORMAL, "Commands sent"))
            } catch (e: Exception) {
                Log.e("@@@WebSocketServer", "Error closing session: ${e.message}")
            } finally {
                clientHandler.removeClient(session)
            }
        }
    }

    /**
     * Сохранение лога жеста.
     *
     * @param report Отчет о выполнении жеста.
     */
    private suspend fun saveLog(report: GestureReport) {
        saveLogUseCase.execute(LogEntry("${report.gestureType}: success - ${report.success}", report.timestamp))
    }
}
