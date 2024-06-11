package com.example.client.presentation.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.client.data.network.WebSocketClient
import com.example.client.domain.usecase.GetConfigUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.util.Log

@HiltViewModel
class MainViewModel @Inject constructor(
    private val webSocketClient: WebSocketClient,
    private val getConfigUseCase: GetConfigUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _isConnected = MutableStateFlow(false)
    val isConnected: StateFlow<Boolean> get() = _isConnected

    init {
        observeConnection()
    }

    /**
     * Наблюдение за состоянием подключения WebSocket клиента.
     */
    private fun observeConnection() {
        viewModelScope.launch {
            webSocketClient.isConnected.collect { connected ->
                _isConnected.value = connected
            }
        }
    }

    /**
     * Подключение к WebSocket серверу с использованием сохраненной конфигурации.
     */
    private fun connect() {
        val ip = getConfigUseCase.getIp() ?: "127.0.0.1" // default IP
        val port = getConfigUseCase.getPort() ?: "8080" // default port
        viewModelScope.launch {
            webSocketClient.connect(ip, port)
        }
    }

    /**
     * Отключение от WebSocket сервера.
     */
    private fun disconnect() {
        viewModelScope.launch {
            webSocketClient.disconnect()
        }
    }

    /**
     * Переключение состояния подключения.
     */
    fun toggleConnection() {
        if (isConnected.value) {
            disconnect()
        } else {
            openGoogleChrome()
            connect()
        }
    }

    /**
     * Открытие Google Chrome с заданным URL.
     */
    private fun openGoogleChrome() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com")).apply {
            setPackage("com.android.chrome")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            Log.e("MainViewModel", "Error opening Google Chrome: ${e.message}")
        }
    }

    /**
     * Получение сохраненного IP адреса.
     *
     * @return Сохраненный IP адрес или null, если он не был сохранен.
     */
    fun getIp(): String? {
        return getConfigUseCase.getIp()
    }

    /**
     * Получение сохраненного порта.
     *
     * @return Сохраненный порт или null, если он не был сохранен.
     */
    fun getPort(): String? {
        return getConfigUseCase.getPort()
    }
}
