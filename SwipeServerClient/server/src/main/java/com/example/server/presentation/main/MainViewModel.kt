package com.example.server.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.server.data.network.WebSocketServer
import com.example.server.domain.usecase.GetConfigUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.net.NetworkInterface
import java.util.Collections
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getConfigUseCase: GetConfigUseCase,
    private val webSocketServer: WebSocketServer
) : ViewModel() {

    private var isServerRunning = false
    val isConnected = MutableStateFlow(false)
    val ipAddress = MutableStateFlow(getDeviceIpAddress())

    /**
     * Запускает WebSocket сервер на указанном порту.
     */
    private fun startServer() {
        viewModelScope.launch(Dispatchers.IO) {
            val port = getConfigUseCase.getPort()?.toInt() ?: 8080 // порт по умолчанию
            webSocketServer.start(port)
        }
        isServerRunning = true
        isConnected.value = true
    }

    /**
     * Останавливает WebSocket сервер.
     */
    private fun stopServer() {
        viewModelScope.launch(Dispatchers.IO) {
            webSocketServer.stop()
        }
        isServerRunning = false
        isConnected.value = false
    }

    /**
     * Переключает состояние сервера (запуск/остановка).
     */
    fun toggleServer() {
        if (isServerRunning) {
            stopServer()
        } else {
            startServer()
        }
    }

    /**
     * Получение сохраненного порта из конфигурации.
     *
     * @return Сохраненный порт или null, если он не был сохранен.
     */
    fun getPort(): String? {
        return getConfigUseCase.getPort()
    }


    /**
     * Получение IP устройства.
     *
     * @return IP
     */
    private fun getDeviceIpAddress(): String {
        val interfaces = Collections.list(NetworkInterface.getNetworkInterfaces())
        for (networkInterface in interfaces) {
            val inetAddresses = Collections.list(networkInterface.inetAddresses)
            for (inetAddress in inetAddresses) {
                if (!inetAddress.isLoopbackAddress) {
                    val ipAddress = inetAddress.hostAddress
                    if (ipAddress != null) {
                        if (ipAddress.contains(':').not()) { // Skip IPv6 addresses
                            return ipAddress
                        }
                    }
                }
            }
        }
        return "Unknown IP"
    }
}